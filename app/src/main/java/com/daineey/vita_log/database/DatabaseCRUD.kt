package com.daineey.vita_log.database

<<<<<<< HEAD
import android.database.Cursor
import android.util.Log
=======
import android.content.SharedPreferences
import android.database.Cursor
import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.util.Random
>>>>>>> ec56cb6 (update 0.22)

class DatabaseCRUD(private val dbHelper: DatabaseHelper) {
    // SELECT
    fun getSupplementByKeyword(keyword: String): List<Supplement> {
        val supplements = mutableListOf<Supplement>()
        val query = """
            SELECT * FROM SUPPLEMENTS 
            JOIN SUPPLEMENT_TAGS ON SUPPLEMENTS.id = SUPPLEMENT_TAGS.supplementId
            JOIN TAGS ON SUPPLEMENT_TAGS.tagId = TAGS.id
            WHERE TAGS.tag = ?
        """

        val db = dbHelper.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, arrayOf(keyword))

            // JAVA's ResultSet
            while (cursor.moveToNext()) {
                val idIndex = cursor.getColumnIndex("id")
                val imageNameIndex = cursor.getColumnIndex("imageName")
                val nameIndex = cursor.getColumnIndex("name")
                val companyIndex = cursor.getColumnIndex("company")
                val ingredientIndex = cursor.getColumnIndex("ingredient")
                val contentIndex = cursor.getColumnIndex("content")
                val formulationIndex = cursor.getColumnIndex("formulation")
                val amountIndex = cursor.getColumnIndex("amount")

                if (idIndex != -1 && imageNameIndex != -1 && nameIndex != -1 && companyIndex != -1) {
                    Log.i("Index", "Index 가 -1이 아닌 경우")
                    val supplement = Supplement(
                        cursor.getInt(idIndex),
                        cursor.getString(imageNameIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(companyIndex),
                        cursor.getString(ingredientIndex),
                        cursor.getString(contentIndex),
                        cursor.getString(formulationIndex),
                        cursor.getString(amountIndex)
                    )
                    supplements.add(supplement)
                } else {
                    Log.i("Index", "값이 없거나 찾을 수 없는 경우")
                }
            }
        } catch (e: Exception) {
            Log.e("Read Error", e.toString())
        } finally {
            cursor?.close()
            db.close()
        }

        return supplements
    }

    // SEARCH
    fun searchForSupplements(keyword: String?): List<Supplement> {
        val results = mutableListOf<Supplement>()
        val db = dbHelper.readableDatabase
        var cursor: Cursor? = null
        try {
            val query = """
            SELECT * FROM SUPPLEMENTS
            WHERE name LIKE ? 
            OR ingredient LIKE ?
            UNION
            SELECT SUPPLEMENTS.* FROM SUPPLEMENTS
            JOIN SUPPLEMENT_TAGS ON SUPPLEMENTS.id = SUPPLEMENT_TAGS.supplementId
            JOIN TAGS ON SUPPLEMENT_TAGS.tagId = TAGS.id
            WHERE TAGS.tag LIKE ?
        """
            cursor = db.rawQuery(query, arrayOf("%$keyword%", "%$keyword%", "%$keyword%"))

            while (cursor.moveToNext()) {
                val idIndex = cursor.getColumnIndex("id")
                val imageNameIndex = cursor.getColumnIndex("imageName")
                val nameIndex = cursor.getColumnIndex("name")
                val companyIndex = cursor.getColumnIndex("company")
                val ingredientIndex = cursor.getColumnIndex("ingredient")
                val contentIndex = cursor.getColumnIndex("content")
                val formulationIndex = cursor.getColumnIndex("formulation")
                val amountIndex = cursor.getColumnIndex("amount")

                val supplement = Supplement(
                    cursor.getInt(idIndex),
                    cursor.getString(imageNameIndex),
                    cursor.getString(nameIndex),
                    cursor.getString(companyIndex),
                    cursor.getString(ingredientIndex),
                    cursor.getString(contentIndex),
                    cursor.getString(formulationIndex),
                    cursor.getString(amountIndex)
                )
                results.add(supplement)
            }
        } catch (e: Exception) {
            Log.e("Read Error", e.toString())
        } finally {
            cursor?.close()
            db.close()
        }
        return results
    }

    fun getSupplementByName(supplementName: String?): Supplement? {
        val db = dbHelper.readableDatabase
<<<<<<< HEAD
        val cursor = db.rawQuery("SELECT * FROM SUPPLEMENTS WHERE name = ?", arrayOf(supplementName))
=======
        val cursor =
            db.rawQuery("SELECT * FROM SUPPLEMENTS WHERE name = ?", arrayOf(supplementName))
>>>>>>> ec56cb6 (update 0.22)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex("id")
            val imageNameIndex = cursor.getColumnIndex("imageName")
            val nameIndex = cursor.getColumnIndex("name")
            val companyIndex = cursor.getColumnIndex("company")
            val ingredientIndex = cursor.getColumnIndex("ingredient")
            val contentIndex = cursor.getColumnIndex("content")
            val formulationIndex = cursor.getColumnIndex("formulation")
            val amountIndex = cursor.getColumnIndex("amount")

            if (idIndex != -1 && imageNameIndex != -1 && nameIndex != -1 && companyIndex != -1 && ingredientIndex != -1 && contentIndex != -1 && formulationIndex != -1 && amountIndex != -1) {
                val supplement = Supplement(
                    id = cursor.getInt(idIndex),
                    imageName = cursor.getString(imageNameIndex),
                    name = cursor.getString(nameIndex),
                    company = cursor.getString(companyIndex),
                    ingredient = cursor.getString(ingredientIndex),
                    content = cursor.getString(contentIndex),
                    formulation = cursor.getString(formulationIndex),
                    amount = cursor.getString(amountIndex)
                )
                cursor.close()
                return supplement
            }
        }
        cursor.close()
        return null
    }
<<<<<<< HEAD
=======

    fun getRandomLank(sharedPreferences: SharedPreferences, count: Int): List<Supplement> {
        val db = dbHelper.readableDatabase
        val gson = Gson()
        val key = "randomList_$count"
        val randomListJson = sharedPreferences.getString(key, null)
        val seed = sharedPreferences.getLong("randomSeed", -1L)

        // SharedPreferences에서 저장된 리스트와 시드를 불러옴
        if (!randomListJson.isNullOrEmpty() && seed != -1L) {
            val type = object : TypeToken<List<Supplement>>() {}.type
            return gson.fromJson(randomListJson, type)
        }

        // 처음이라면 시드를 생성하고 저장
        val newSeed = seed.takeIf { it != -1L } ?: System.currentTimeMillis()
        with(sharedPreferences.edit()) {
            putLong("randomSeed", newSeed)
            apply()
        }

        // 데이터베이스에서 모든 영양제를 불러옴
        val supplements = mutableListOf<Supplement>()
        val cursor = db.rawQuery("SELECT * FROM SUPPLEMENTS", null)

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex("id")
                val imageNameIndex = cursor.getColumnIndex("imageName")
                val nameIndex = cursor.getColumnIndex("name")
                val companyIndex = cursor.getColumnIndex("company")
                val ingredientIndex = cursor.getColumnIndex("ingredient")
                val contentIndex = cursor.getColumnIndex("content")
                val formulationIndex = cursor.getColumnIndex("formulation")
                val amountIndex = cursor.getColumnIndex("amount")

                val supplement = Supplement(
                    cursor.getInt(idIndex),
                    cursor.getString(imageNameIndex),
                    cursor.getString(nameIndex),
                    cursor.getString(companyIndex),
                    cursor.getString(ingredientIndex),
                    cursor.getString(contentIndex),
                    cursor.getString(formulationIndex),
                    cursor.getString(amountIndex)
                )
                supplements.add(supplement)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        // 동일한 시드를 사용하여 리스트를 섞음
        val random = Random(newSeed)
        val shuffledSupplements = supplements.shuffled(random).take(count)

        // 섞인 리스트를 SharedPreferences에 저장
        with(sharedPreferences.edit()) {
            val json = gson.toJson(shuffledSupplements)
            putString(key, json)
            apply()
        }

        return shuffledSupplements
    }

>>>>>>> ec56cb6 (update 0.22)
}