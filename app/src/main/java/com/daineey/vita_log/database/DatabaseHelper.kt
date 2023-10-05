package com.daineey.vita_log.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.daineey.vita_log.R
import org.xmlpull.v1.XmlPullParser

// Data Class Declare
data class Supplement(
    var id: Int = -1,
    var imageName: String,
    val imagePath: String,
    var name: String,
    var company: String,
    var ingredient: String,
    var content: String,
    var formulation: String,
    var amount: String
)

// Tag Data Class
data class Tag(
    val id: Int,
    val name: String
)

// Supplement to Tag Relationship Data Class
data class SupplementTag(
    val supplementId: Int,
    val tagId: Int
)

class DatabaseHelper(
    // 전역 scope 선언
    private val context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        // 기본 영양제 정보 Table
        val createSupplementTable: String = "CREATE TABLE SUPPLEMENTS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "imageName TEXT," +
                "imagePath TEXT," +
                "name TEXT NOT NULL," +
                "company TEXT," +
                "ingredient TEXT," +
                "content TEXT," +
                "formulation TEXT," +
                "amount TEXT" +
                ")"
        db.execSQL(createSupplementTable)

        // 장건강, 전립선 등 키워드 선언용 Table
        val createTagTable: String = "CREATE TABLE TAGS(" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL UNIQUE" +
                ")"
        db.execSQL(createTagTable)

        // SUPPLEMENTS & TAGS JOIN 전용 = N:M 관계
        val createSupplementTagTable: String = "CREATE TABLE SUPPLEMENT_TAGS(" +
                "supplementId INTEGER," +
                "tagId INTEGER," +
                "PRIMARY KEY(supplementId, tagId)," +
                "FOREIGN KEY(supplementId) REFERENCES SUPPLEMENTS(id)," +
                "FOREIGN KEY(tagId) REFERENCES TAGS(id)" +
                ")"
        db.execSQL(createSupplementTagTable)
    }

    /** @return XML Parse -> data class Supplement List */
    private fun parseSupplementData(context: Context?): List<Supplement> {
        val supplements = mutableListOf<Supplement>()
        val parser = context?.resources?.getXml(R.xml.suplements)

        var eventType = parser?.eventType
        var supplement: Supplement? = null
        var tagName: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                // if xml <supplement> Start -> TEXT Approach
                XmlPullParser.START_TAG -> {
                    tagName = parser?.name
                    if (parser?.name == "supplement") {
                        supplement = Supplement(
                            company = "", ingredient = "", content = "",
                            imageName = "", imagePath = "", name = "",
                            formulation = "", amount = ""
                        )
                    }
                }
                // <supplement>${DATA}</supplement> DATA Parsing
                XmlPullParser.TEXT -> {
                    val textValue = parser?.text?.trim() ?: ""
                    if (textValue.isNotEmpty()) {
                        when (tagName) {
                            "image" -> supplement?.imageName = textValue
                            "name" -> supplement?.name = textValue
                            "company" -> supplement?.company = textValue
                            "ingredient" -> supplement?.ingredient = textValue
                            "content" -> supplement?.content = textValue
                            "formulation" -> supplement?.formulation = textValue
                            "amount" -> supplement?.amount = textValue
                        }
                    }
                }
                // if xml </supplement> TAG -> Jump to Next
                XmlPullParser.END_TAG -> {
                    if (parser?.name == "supplement") {
                        supplement?.let {
                            it.id =
                                supplements.size + 1  // Set ID based on list size (can be modified based on your requirements)
                            supplements.add(it)
                        }
                    }
                }
            }
            eventType = parser?.next()
        }
        return supplements
    }

    private fun insertInitialData(db: SQLiteDatabase) {
        // TABLE: SUPPLEMENTS
        val supplements = parseSupplementData(context)

        // TABLE: TAGS / 태그 초기 데이터 입력 & 단어 to 단어 가운데 spacing(ex 장 건강, 눈 건강 ..)
        val initialTags = listOf("장 건강", "전립선", "일상 활력", "수면", "운동 보조제", "피부 보조제", "눈 건강", "간 건강")
        val tagIds = mutableListOf<Long>()
        for (tagName in initialTags) {
            val contentValues = ContentValues().apply {
                put("name", tagName)
            }
            val id = db.insert("TAGS", null, contentValues)
            tagIds.add(id)
        }

        // supplement 데이터 입력
        for (supplement in supplements) {
            val contentValues = ContentValues().apply {
                put("imageName", supplement.imageName)
            }
            val supplementId = db.insert("SUPPLEMENTS", null, contentValues)

            // 각 Supplement와 관련된 태그와의 연결을 SUPPLEMENT_TAGS에 추가
            for (tagId in tagIds) {
                val relationContentValues = ContentValues().apply {
                    put("supplementId", supplementId)
                    put("tagId", tagId)
                }
                db.insert("SUPPLEMENT_TAGS", null, relationContentValues)
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // If DB Version Upgrade -> DB Drop & Recreate
        db.execSQL("DROP TABLE IF EXISTS SUPPLEMENTS")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "SupplementsDB"
        private const val DATABASE_VERSION = 1
    }
}