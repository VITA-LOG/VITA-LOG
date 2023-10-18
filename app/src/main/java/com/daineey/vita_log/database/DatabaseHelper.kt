package com.daineey.vita_log.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.daineey.vita_log.R
import org.xmlpull.v1.XmlPullParser

// Data Class Declare
data class Supplement(
    var id: Int = -1,
    var imageName: String,
    var name: String,
    var company: String,
    var ingredient: String,
    var content: String,
    var formulation: String,
    var amount: String
)

// Tag Data Class
data class Tag(
    val id: Int, val tag: String
)

// Supplement to Tag Relationship Data Class
data class SupplementTag(
    val supplementId: Int, val tagId: Int
)

class DatabaseHelper(
    // 전역 scope 선언
    private val context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        // 기본 영양제 정보 Table
        val createSupplementTable: String =
            "CREATE TABLE SUPPLEMENTS(" + "id INTEGER PRIMARY KEY," + "imageName TEXT," + "name TEXT NOT NULL," + "company TEXT," + "ingredient TEXT," + "content TEXT," + "formulation TEXT," + "amount TEXT" + ")"
        db.execSQL(createSupplementTable)

        // 장건강, 전립선 등 키워드 선언용 Table
        val createTagTable: String =
            "CREATE TABLE TAGS(" + "id INTEGER PRIMARY KEY," + "tag TEXT NOT NULL UNIQUE" + ")"
        db.execSQL(createTagTable)

        // SUPPLEMENTS & TAGS JOIN 전용 = N:M 관계
        val createSupplementTagTable: String =
            "CREATE TABLE SUPPLEMENT_TAGS(" + "supplementId INTEGER," + "tagId INTEGER," + "PRIMARY KEY(supplementId, tagId)," + "FOREIGN KEY(supplementId) REFERENCES SUPPLEMENTS(id)," + "FOREIGN KEY(tagId) REFERENCES TAGS(id)" + ")"
        db.execSQL(createSupplementTagTable)

        // 초기 데이터 삽입
        insertInitialData(db);
    }

    /** @return XML Parse -> data class Supplement List */
    private fun parseSupplementData(context: Context?): List<Pair<Supplement, List<String>>> {
        val supplementsWithTags = mutableListOf<Pair<Supplement, List<String>>>()
        val parser = context?.resources?.getXml(R.xml.suplements)

        var eventType = parser?.eventType
        var supplement: Supplement? = null
        var tagName: String? = null
        var tags = mutableListOf<String>()

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                // if xml <supplement> Start -> TEXT Approach
                XmlPullParser.START_TAG -> {
                    tagName = parser?.name
                    if (parser?.name == "supplement") {
                        supplement = Supplement(
                            company = "",
                            ingredient = "",
                            content = "",
                            imageName = "",
                            name = "",
                            formulation = "",
                            amount = ""
                        )
                    }
                }
                // <supplement>${DATA}</supplement> DATA Parsing
                XmlPullParser.TEXT -> {
                    val textValue = parser?.text?.trim() ?: ""
                    if (textValue.isNotEmpty()) {
                        when (tagName) {
                            "image" -> {
                                supplement?.imageName = textValue.removeSuffix(".jpg")
                            }

                            "name" -> supplement?.name = textValue
                            "company" -> supplement?.company = textValue
                            "ingredient" -> supplement?.ingredient = textValue
                            "content" -> supplement?.content = textValue
                            "formulation" -> supplement?.formulation = textValue
                            "amount" -> supplement?.amount = textValue
                            "tag" -> tags.add(parser!!.text.trim())
                        }
                    }
                }
                // if xml </supplement> TAG -> Jump to Next
                XmlPullParser.END_TAG -> {
                    when (parser?.name) {
                        "supplement" -> {
                            supplement?.let {
                                supplementsWithTags.add(Pair(it, tags))
                                tags = mutableListOf()  // Reset for next supplement
                            }
                        }
                    }
                }
            }
            eventType = parser?.next()
        }
        return supplementsWithTags
    }

    // 초기 데이터 Insert
    private fun insertInitialData(db: SQLiteDatabase) {
        // TABLE: SUPPLEMENTS
        val supplementsWithTags = parseSupplementData(context)

        // supplement 데이터 입력
        for ((supplement, tags) in supplementsWithTags) {
            val contentValues = ContentValues().apply {
                put("imageName", supplement.imageName)
                put("name", supplement.name)
                put("company", supplement.company)
                put("ingredient", supplement.ingredient)
                put("content", supplement.content)
                put("formulation", supplement.formulation)
                put("amount", supplement.amount)
            }
            val supplementId = db.insert("SUPPLEMENTS", null, contentValues)

            // 각 Supplement와 관련된 태그와의 연결을 SUPPLEMENT_TAGS에 추가
            for (tagName in tags) {
                val tagIdCursor =
                    db.rawQuery("SELECT id FROM TAGS WHERE tag = ?", arrayOf(tagName))

                var tagId: Int? = null
                if (tagIdCursor.moveToFirst()) {
                    tagId = tagIdCursor.getInt(0)
                }
                tagIdCursor.close()

                // 해당 태그가 TAGS 테이블에 없다면 삽입
                if (tagId == null) {
                    val tagValues = ContentValues().apply {
                        put("tag", tagName)
                    }
                    tagId = db.insert("TAGS", null, tagValues).toInt()
                }

                // SUPPLEMENT_TAGS 테이블에 연결 정보 추가
                val relationContentValues = ContentValues().apply {
                    put("supplementId", supplementId)
                    put("tagId", tagId)
                }

                db.insert("SUPPLEMENT_TAGS", null, relationContentValues)
                tagIdCursor.close()
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // If DB Version Upgrade -> DB Drop & Recreate
        db.execSQL("DROP TABLE IF EXISTS SUPPLEMENTS")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "SupplementDB"
        const val DATABASE_VERSION = 1
    }
}