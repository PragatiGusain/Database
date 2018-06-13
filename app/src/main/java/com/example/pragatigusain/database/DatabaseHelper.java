package com.example.pragatigusain.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="student_db";

    private static final String TABLE_NAME="student_record";
    private static final String STUDENT_NAME="student_name";
    private static final String STUDENT_ID="student_id";
    private static final String STUDENT_COLLEGE="student_college";
    private static final String STUDENT_ADDRESS="student_address";
    private static final String STUDENT_PHONE="student_phone";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            STUDENT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT, " +
            STUDENT_COLLEGE + " TEXT, " + STUDENT_ADDRESS
            + " TEXT, " + STUDENT_PHONE + " LONG ); ";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public long addNewStudent(Student student)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENT_NAME, student.name);
        contentValues.put(STUDENT_COLLEGE, student.college);
        contentValues.put(STUDENT_ADDRESS, student.address);
        contentValues.put(STUDENT_PHONE, student.phone);

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }

    public Student getSingleStudentDetails(long id)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{STUDENT_ID, STUDENT_NAME, STUDENT_COLLEGE, STUDENT_ADDRESS,
                        STUDENT_PHONE}, STUDENT_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        Student student = new Student(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)),
                cursor.getString(cursor.getColumnIndex(STUDENT_NAME)), cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)),
                cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)), cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE)));


        cursor.close();

        return student;
    }

    public List<Student> allStudentsDetails()
    {
        List<Student> studentsList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                student.setCollege(cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)));
                student.setAddress(cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)));
                student.setPhone(cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE)));

                studentsList.add(student);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return  studentsList;
    }

    public int getStudentsCount()
    {

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int totalStudentsCount = cursor.getCount();
        cursor.close();

        return totalStudentsCount;
    }

    public int updateIndividualStudentDetails(Student student)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_COLLEGE, student.getCollege());
        values.put(STUDENT_ADDRESS, student.getAddress());
        values.put(STUDENT_PHONE, student.getPhone());

        return sqLiteDatabase.update(TABLE_NAME, values, STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
    }

}


