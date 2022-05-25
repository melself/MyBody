package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.melself.mybody.R;

public class SaveTaskHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "save_task.db";
    public static final int DB_VERSION = 1;


    public SaveTaskHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TASKS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "D1 INTEGER, "
                + "D2 INTEGER, "
                + "D3 INTEGER, "
                + "D4 INTEGER, "
                + "D5 INTEGER, "
                + "D6 INTEGER, "
                + "D7 INTEGER, "
                + "IMG INTEGER);");
        insertTask(db, "Приседание со штангой", "Приседания со штангой – базовый элемент любой тяжелоатлетической тренировки. Упражнение всесторонне развивает фигуру и увеличивает силовые показатели спортсмена.",0,0,0,0,0,0,0,R.drawable.prisedshtang);
        insertTask(db, "Подъем гантелей плечами", "Шаг 1. Встаньте прямо, руки опустите по бокам, возьмите в каждую по тяжелой гантеле. Шаг 2. Поднимите плечи как можно выше, словно пытаясь достать ими до ушей. Шаг 3. Задержитесь в верхней точке, затем расслабьтесь и вернитесь в исходное положение.",0,0,0,0,0,0,0,R.drawable.second);
        insertTask(db, "Жим ног", "Тренажер для жима ногами под углом – один из самых распространенных станков во всех фитнес-клубах мира. Во время выполнения угол между торсом спортсмена и платформой равен примерно 45 градусам.",0,0,0,0,0,0,0, R.drawable.zhimnog);
        insertTask(db, "Разгибание ног в тренажёре", "Разгибание ног в тренажере относится к изолирующему типу упражнений. Оно направлено на проработку четырехглавой мышцы бедра, но может служить в качестве разогрева перед тяжелыми базовыми элементами либо завершать тренировки, дорабатывая уставшую мышцу до конца.",0,0,0,0,0,0,0,R.drawable.four);
        insertTask(db, "Жим от плеч", "Жим от плеч сидя в тренажёре – силовое, многосуставное упражнение, направленное на проработку дельтовидных мышц. Основные рабочие мышечные группы: плечи. Вспомогательная мышечная группа: трицепсы.",0,0,0,0,0,0,0,R.drawable.five);
        insertTask(db, "Подъем веса икрами", "Среди всех упражнений, направленных на развитие икроножных мышц, это можно назвать условно комплексным и самым тяжелым.",0,0,0,0,0,0,0,R.drawable.six);
        insertTask(db, "Поднятие ног вися", "Повисните на турнике, взгляд направлен перед собой, тело находится в вертикальном положении\n" +
                "Начинайте поднимать ноги вперед до уровня параллели с полом\n" +
                "После выдоха сделайте небольшую паузу (0.5-1 секунды) в пиковой точке, после чего медленно и подконтрольно опустите ноги вниз",0,0,0,0,0,0,0,R.drawable.seven);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private static void insertTask(SQLiteDatabase db, String name, String description, int d1, int d2, int d3, int d4, int d5, int d6, int d7, int img){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("D1", d1);
        contentValues.put("D2", d2);
        contentValues.put("D3", d3);
        contentValues.put("D4", d4);
        contentValues.put("D5", d5);
        contentValues.put("D6", d6);
        contentValues.put("D7", d7);
        contentValues.put("IMG", img);
        db.insert("TASKS", null, contentValues);
    }

    public void updateD1(SQLiteDatabase db, String name, int d1) {
        ContentValues values = new ContentValues();
        values.put("D1", d1);
        db.update("TASKS", values, "name=?", new String[]{name});
    }

    public void updateD2(SQLiteDatabase db, String name, int d2) {
        ContentValues values = new ContentValues();
        values.put("D2", d2);
        db.update("TASKS", values, "name=?", new String[]{name});
    }

    public void updateD3(SQLiteDatabase db, String name, int d3) {
        ContentValues values = new ContentValues();
        values.put("D3", d3);
        db.update("TASKS", values, "name=?", new String[]{name});
    }

    public void updateD4(SQLiteDatabase db, String name, int d4) {
        ContentValues values = new ContentValues();
        values.put("D4", d4);
        db.update("TASKS", values, "name=?", new String[]{name});
    }

    public void updateD5(SQLiteDatabase db, String name, int d5) {
        ContentValues values = new ContentValues();
        values.put("D5", d5);
        db.update("TASKS", values, "name=?", new String[]{name});
    }

    public void updateD6(SQLiteDatabase db, String name, int d6) {
        ContentValues values = new ContentValues();
        values.put("D6", d6);
        db.update("TASKS", values, "name=?", new String[]{name});
    }

    public void updateD7(SQLiteDatabase db, String name, int d7) {
        ContentValues values = new ContentValues();
        values.put("D7", d7);
        db.update("TASKS", values, "name=?", new String[]{name});
    }
}
