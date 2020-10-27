package com.example.roomexample.models;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ResultDao_Impl implements ResultDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Result> __insertionAdapterOfResult;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ResultDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfResult = new EntityInsertionAdapter<Result>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `result` (`uid`,`fichas`,`nombre`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Result value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getFichas());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM result";
        return _query;
      }
    };
  }

  @Override
  public List<Long> insert(final Result... results) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfResult.insertAndReturnIdsList(results);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Result> getAll() {
    final String _sql = "SELECT * FROM result";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfFichas = CursorUtil.getColumnIndexOrThrow(_cursor, "fichas");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final List<Result> _result = new ArrayList<Result>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Result _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpFichas;
        _tmpFichas = _cursor.getInt(_cursorIndexOfFichas);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item = new Result(_tmpUid,_tmpFichas,_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
