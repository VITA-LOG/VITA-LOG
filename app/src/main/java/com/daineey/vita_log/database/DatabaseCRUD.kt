package com.daineey.vita_log.database

import android.database.Cursor
import android.util.Log

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
        val cursor = db.rawQuery("SELECT * FROM SUPPLEMENTS WHERE name = ?", arrayOf(supplementName))

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
}