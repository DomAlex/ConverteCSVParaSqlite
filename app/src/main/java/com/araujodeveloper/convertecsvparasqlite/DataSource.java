package com.araujodeveloper.convertecsvparasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alex Araujo de Paula on 14/04/16.
 */
public class DataSource extends SQLiteOpenHelper {
    private static final String DB_NAME = "sinalizacao";
    private static final int DB_VERSION = 1;

    SQLiteDatabase database;
    Cursor cursor = null;

    public DataSource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* final String criarTabelaPontos = "CREATE TABLE pontos(_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, latitude TEXT, longitude TEXT, endereco TEXT, tipo INTEGER, situacao_estrutura INTEGER, "+
        "iluminacao_interna INTEGER, iluminacao_interna_reparo INTEGER, iluminacao_publica INTEGER, iluminacao_publica_situacao INTEGER, iluminacao_publica_instalacao_reparo INTEGER,"+
        " observacoes_estrutura TEXT, observacoes_iluminacao_interna TEXT, observacoes_iluminacao_publica TEXT)";*/

        final String criarTabelaFotos = "CREATE TABLE fotos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, id_local INTEGER, caminho TEXT)";


        final String criaTabelaCruzamentos = "CREATE TABLE cruzamentos (_id INTEGER PRIMARY KEY, cruzamento VARCHAR(200)," +
                " latitude NUMERIC(10,5), longitude NUMERIC(10,5))";

        final String criaTabelaSinalizacaoVertical = "CREATE TABLE sinalizacao_vertical (_id INTEGER PRIMARY KEY, id_local INTEGER, id_placa INTEGER, grau_de_engenharia INTEGER, fixacao TEXT," +
                "estado TEXT, observacoes TEXT,data_do_registro TEXT)";

        final String criaTabelaSinalizacaoHorizontal = "CREATE TABLE sinalizacao_horizontal (_id INTEGER PRIMARY KEY, id_local INTEGER, grupo TEXT, tipo TEXT, tracado TEXT," +
                "tipo_tracejo TEXT, cor TEXT, material_utilizado TEXT, estado TEXT, observacoes TEXT, data_do_registro TEXT)";

        final String criaTabelaLocal = "CREATE TABLE local (_id INTEGER PRIMARY KEY, tipo TEXT,  latitude REAL,  longitude  REAL)";

        final String criaTabelaSegmentos = "CREATE TABLE segmentos (_id INTEGER PRIAMRY KEY, nome TEXT, rua_1 TEXT, rua_2 TEXT, latitude REAL,  longitude REAL)";

        /*final String criaTabelaRepetidor = "CREATE TABLE repetidor (identificacao NUMERIC, _id INTEGER PRIMARY KEY, id_grupo INTEGER, tipo INTEGER, red INTEGER," +
                " yellow INTEGER, green INTEGER, lentes INTEGER, pestanas INTEGER, pest_qt INTEGER, obs TEXT)";

        final String criaTabelaFotoGrupoSemaforico = "CREATE TABLE foto_grupo_semaforico(id_cruzamento INTEGER, _id INTEGER PRIMARY KEY, id_grupo_semaforico INTEGER," +
                " nome_da_foto TEXT, caminho TEXT)";

        final String criaTabelaFotoControladora = "CREATE TABLE foto_controladora(id_cruzamento INTEGER, _id INTEGER PRIMARY KEY, id_controladora INTEGER, nome_da_foto TEXT," +
                " caminho TEXT)";

        final String criaTabelaFoco = "CREATE TABLE  foco(identificacao NUMERIC, _id INTEGER PRIMARY KEY, id_grupo INTEGER, tipo INTEGER, anteparo INTEGER, red INTEGER," +
                " yellow INTEGER, green INTEGER, lentes INTEGER, pestanas INTEGER, pest_qt INTEGER, obs TEXT)";

        final String criaTabelaEstrutura = "CREATE TABLE estrutura(identificacao NUMERIC, _id INTEGER PRIMARY KEY, id_grupo INTEGER, tipo INTEGER, estado INTEGER, coluna INTEGER," +
                " braco INTEGER, eletrica INTEGER)";

        final String criaTabelaControladora = "CREATE TABLE  controladora (identificacao NUMERIC, _id INTEGER PRIMARY KEY, marca VARCHAR(40), modelo VARCHAR(40), serie VARCHAR(40)," +
                "local VARCHAR(150), radar INTEGER, instalacao INTEGER, dps INTEGER, placas INTEGER, fonte INTEGER, pla_com INTEGER, cpu INTEGER, gps INTEGER, obs TEXT)";

        final String criaTabelaGrupo = "CREATE TABLE grupo(_id INTEGER PRIMARY KEY, id_cruza INTEGER, sentido VARCHAR(30))";


        final String criaTabelaPedestre = "CREATE TABLE pedestre(obs TEXT, identificacao NUMERIC, _id INTEGER PRIMARY KEY, id_grupo INTEGER, tipo INTEGER, pestanas INTEGER, red INTEGER," +
                " green INTEGER, lentes INTEGER, cronometro INTEGER)";

        final String criaTabelaConcessionaria = "CREATE TABLE concessionaria(cond_caixa_de_passagem TEXT, _id INTEGER PRIMARY KEY, id_control INTEGER, medidor VARCHAR(20), " +
                "identificacao VARCHAR(20), unidade VARCHAR(20), aterramento INTEGER, potencia NUMERIC(7,2), obs TEXT)";

        final String criaTabelaMedidor = "CREATE TABLE medidor(_id INTEGER PRIMARY KEY, num_medidor TEXT, un_consumidora INTEGER, cruzamento TEXT)";
*/










        db.beginTransaction();
        try {
            db.execSQL(criarTabelaFotos);
            db.execSQL(criaTabelaCruzamentos);
            db.execSQL(criaTabelaLocal);
            db.execSQL(criaTabelaSegmentos);
            db.execSQL(criaTabelaSinalizacaoHorizontal);
            db.execSQL(criaTabelaSinalizacaoVertical);

            /*db.execSQL(criaTabelaEstrutura);
            db.execSQL(criaTabelaFoco);
            db.execSQL(criaTabelaFotoControladora);
            db.execSQL(criaTabelaFotoGrupoSemaforico);
            db.execSQL(criaTabelaGrupo);
            db.execSQL(criaTabelaMedidor);
            db.execSQL(criaTabelaPedestre);
            db.execSQL(criaTabelaRepetidor);
            db.execSQL(criarTabelaPontos);
            db.execSQL(criaTabelaConcessionaria);
            db.execSQL(criaTabelaControladora);*/
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Erro","Erro",e);
            db.endTransaction();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String deletarTabelaPontos = "DROP TABLE pontos";
        db.beginTransaction();
        try {
            db.execSQL(deletarTabelaPontos);
            onCreate(db);
            db.setTransactionSuccessful();
        }catch (Exception e){
            db.endTransaction();
        }finally {
            db.endTransaction();
        }
    }


    public void inserir(ContentValues cv, String tabela){
        database = getWritableDatabase();

        database.beginTransaction();
        try {
            database.insert(tabela,null,cv);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("ErroAoInserirNoBanco","Erro ao inserir no banco de dados",e);
            database.endTransaction();
        }finally {
            database.endTransaction();
        }
    }



    //--------------ALTERAR--------------
    public void alterar (String tabela, ContentValues cv,String where){
        database = getWritableDatabase();

        database.beginTransaction();
        try {
            database.update(tabela, cv, where, null);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("ErroAoAlterarNoBanco","Erro ao alterar no banco de dados",e);
            database.endTransaction();
        }finally {
            database.endTransaction();
        }
    }

    //------------DELETAR---------------
    public void deletar(String tabela, String where){
        database = getWritableDatabase();

        database.beginTransaction();
        try {
            database.delete(tabela, where, null);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("ErroAoDeletarDoBanco","Erro ao deletar no banco de dados",e);
            database.endTransaction();
        }finally {
            database.endTransaction();
        }

    }

    //--------------RETORNO-------------
    public Cursor retorno (String query){
        database = getReadableDatabase();


        database.beginTransaction();
        try {
            cursor = database.rawQuery(query, null);
            database.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("ErroAoRetornarDados","Erro ao retornar dados",e);
            database.endTransaction();
        }finally {
            database.endTransaction();
        }

        return cursor;
    }
}
