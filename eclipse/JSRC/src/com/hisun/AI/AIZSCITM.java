package com.hisun.AI;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSCITM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm AITITAD_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_COL_CNT = 20;
    AIZSCITM_WS_OUT_LIST WS_OUT_LIST = new AIZSCITM_WS_OUT_LIST();
    AIZSCITM_WS_ITM_STS WS_ITM_STS = new AIZSCITM_WS_ITM_STS();
    char WS_REC_HAVE = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRITAD AIRITAD = new AIRITAD();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQITM AICQITM = new AICPQITM();
    SCCGWA SCCGWA;
    AICSCITM AICSCITM;
    AIRITAD AIRLITAD;
    String WS_FUNC = " ";
    public void MP(SCCGWA SCCGWA, AICSCITM AICSCITM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSCITM = AICSCITM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSCITM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AIRLITAD = (AIRITAD) AICSCITM.REC_PTR;
        AICSCITM.RETURN_INFO = 'F';
        WS_REC_HAVE = 'N';
        IBS.init(SCCGWA, AIRITAD);
        CEP.TRC(SCCGWA, AICSCITM.REC_LEN);
        AICSCITM.RC.RC_MMO = "AI";
        AICSCITM.RC.RC_CODE = 0;
        AIRITAD.KEY.PROC_DATE = AICSCITM.COMM_DATA.ADJ_DATE;
        AIRITAD.KEY.COA_FLG = AICSCITM.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSCITM.COMM_DATA.ITM_OLD;
        AIRITAD.DATA = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        if ((AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            if (AIRITAD.DATA == null) AIRITAD.DATA = "";
            JIBS_tmp_int = AIRITAD.DATA.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) AIRITAD.DATA += " ";
            if (AICSCITM.COMM_DATA.ITM_NEW == null) AICSCITM.COMM_DATA.ITM_NEW = "";
            JIBS_tmp_int = AICSCITM.COMM_DATA.ITM_NEW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AICSCITM.COMM_DATA.ITM_NEW += " ";
            AIRITAD.DATA = AICSCITM.COMM_DATA.ITM_NEW + AIRITAD.DATA.substring(10);
            if (AIRITAD.DATA == null) AIRITAD.DATA = "";
            JIBS_tmp_int = AIRITAD.DATA.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) AIRITAD.DATA += " ";
            JIBS_tmp_str[0] = "" + AICSCITM.COMM_DATA.PRC_STS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            AIRITAD.DATA = AIRITAD.DATA.substring(0, 11 - 1) + JIBS_tmp_str[0] + AIRITAD.DATA.substring(11 + 1 - 1);
        }
        if ((AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            if (AIRITAD.DATA == null) AIRITAD.DATA = "";
            JIBS_tmp_int = AIRITAD.DATA.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) AIRITAD.DATA += " ";
            JIBS_tmp_str[0] = "" + AICSCITM.COMM_DATA.PRC_STS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            AIRITAD.DATA = AIRITAD.DATA.substring(0, 11 - 1) + JIBS_tmp_str[0] + AIRITAD.DATA.substring(11 + 1 - 1);
        }
        if ((AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            if (AIRITAD.DATA == null) AIRITAD.DATA = "";
            JIBS_tmp_int = AIRITAD.DATA.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) AIRITAD.DATA += " ";
            if (AICSCITM.COMM_DATA.ITM_NEW == null) AICSCITM.COMM_DATA.ITM_NEW = "";
            JIBS_tmp_int = AICSCITM.COMM_DATA.ITM_NEW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AICSCITM.COMM_DATA.ITM_NEW += " ";
            AIRITAD.DATA = AICSCITM.COMM_DATA.ITM_NEW + AIRITAD.DATA.substring(10);
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM++++++++++++++++++++++++++++++1");
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.ADJ_DATE);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.ITM_OLD);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.ITM_NEW);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.PRC_STS);
        CEP.TRC(SCCGWA, AICSCITM.COMM_DATA.COA_TYP);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        C100_MPAG_START();
        if (pgmRtn) return;
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            CEP.TRC(SCCGWA, "STS---------1");
            T000_COL();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            CEP.TRC(SCCGWA, "STS---------2");
            T000_COL_DAT();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            CEP.TRC(SCCGWA, "STS---------3");
            T000_COL_OLD();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            T000_COL_NEW();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            T000_COL_DAT_OLD();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            T000_COL_DAT_NEW();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_DAT_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            T000_COL_OLD_NEW();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_OLD_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_STS_NEW();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS == ' ')) {
            T000_COL_DAT_OLD_NEW();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() == 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_DAT_OLD_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() == 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_DAT_NEW_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE == 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_OLD_NEW_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AICSCITM.COMM_DATA.ADJ_DATE != 0 
            && AICSCITM.COMM_DATA.ITM_OLD.trim().length() > 0 
            && AICSCITM.COMM_DATA.ITM_NEW.trim().length() > 0 
            && AICSCITM.COMM_DATA.PRC_STS != ' ')) {
            T000_COL_DAT_OLD_NEW_STS();
            if (pgmRtn) return;
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C100_MPAG_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 359;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_COL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL");
        AIRITAD.KEY.COA_FLG = AICSCITM.COMM_DATA.COA_TYP;
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "COA_FLG = :AIRITAD.KEY.COA_FLG";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT");
        AIRITAD.KEY.COA_FLG = AICSCITM.COMM_DATA.COA_TYP;
        AIRITAD.KEY.PROC_DATE = AICSCITM.COMM_DATA.ADJ_DATE;
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_OLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-OLD");
        AIRITAD.KEY.COA_FLG = AICSCITM.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSCITM.COMM_DATA.ITM_OLD;
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM-----------------T000-COL-NEW");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG";
        AITITAD_BR.rp.order = "COA_FLG";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG";
        AITITAD_BR.rp.order = "COA_FLG";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        CEP.TRC(SCCGWA, "ZSCITM------------------LBY1");
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ZSCITM------------------LBY2");
    }
    public void T000_COL_DAT_OLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-OLD");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-NEW");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_OLD_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-OLD-NEW");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_OLD_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-OLD-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_STS_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-STS-NEW");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG";
        AITITAD_BR.rp.order = "COA_FLG";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT_OLD_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-OLD-NEW");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT_OLD_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-OLD-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT_NEW_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-NEW-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_OLD_NEW_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-OLD-NEW-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_COL_DAT_OLD_NEW_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------T000-COL-DAT-OLD-NEW-STS");
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "DATA LIKE :AIRITAD.DATA "
            + "AND PROC_DATE = :AIRITAD.KEY.PROC_DATE "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITAD.KEY.ITM_NO";
        AITITAD_BR.rp.order = "COA_FLG,PROC_DATE,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
        T010_STARTBR_DBIO_PROC();
        if (pgmRtn) return;
    }
    public void T000_OUTPUT_CYCLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZSCITM------------------2");
        T000_READNEXT_AITITAD();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (AIRITAD.FUNC_FLG == '1') {
                D100_DATA_OUTPUT();
                if (pgmRtn) return;
                D100_DATA_PAGE_OUT();
                if (pgmRtn) return;
            }
            T000_READNEXT_AITITAD();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITITAD();
        if (pgmRtn) return;
    }
    public void D100_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "5170---------+-------------");
        IBS.init(SCCGWA, WS_OUT_LIST);
        IBS.init(SCCGWA, WS_ITM_STS);
        WS_OUT_LIST.WS_ADJ_DATE = AIRITAD.KEY.PROC_DATE;
        IBS.CPY2CLS(SCCGWA, AIRITAD.DATA, WS_ITM_STS);
        WS_OUT_LIST.WS_COA_TYP = AIRITAD.KEY.COA_FLG;
        WS_OUT_LIST.WS_ITM_OLD = AIRITAD.KEY.ITM_NO;
        WS_OUT_LIST.WS_ITM_NEW = WS_ITM_STS.WS_ITM_STR;
        WS_OUT_LIST.WS_STS_FLG = WS_ITM_STS.WS_STS_STR;
        WS_OUT_LIST.WS_ITMNEW_S = WS_ITM_STS.WS_SEQ_STR;
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.COA_FLG = AIRITAD.KEY.COA_FLG;
        AICQITM.INPUT_DATA.NO = AIRITAD.KEY.ITM_NO;
        CEP.TRC(SCCGWA, "----------++++++++++++");
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        S100_CALL_AIZPQITM();
        if (pgmRtn) return;
        if (AICQITM.RC.RTNCODE == 0) {
            WS_OUT_LIST.WS_ITM_OLD_NAM = AICQITM.OUTPUT_DATA.CHS_NM;
        }
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.COA_FLG = AIRITAD.KEY.COA_FLG;
        AICQITM.INPUT_DATA.NO = WS_ITM_STS.WS_ITM_STR;
        CEP.TRC(SCCGWA, "----------=========");
        CEP.TRC(SCCGWA, WS_ITM_STS.WS_ITM_STR);
        S100_CALL_AIZPQITM();
        if (pgmRtn) return;
        if (AICQITM.RC.RTNCODE == 0) {
            WS_OUT_LIST.WS_ITM_NEW_NAM = AICQITM.OUTPUT_DATA.CHS_NM;
        }
        CEP.TRC(SCCGWA, WS_ITM_STS);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ADJ_DATE);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_COA_TYP);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITM_OLD);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITM_NEW);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_STS_FLG);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITMNEW_S);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITM_OLD_NAM);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITM_NEW_NAM);
    }
    public void D100_DATA_PAGE_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_LIST);
        SCCMPAG.DATA_LEN = 359;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_ENDBR_AITITAD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITITAD_BR);
    }
    public void T010_STARTBR_DBIO_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICSCITM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICSCITM.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITAD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITITAD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRITAD, this, AITITAD_BR);
        CEP.TRC(SCCGWA, "AIZSCITM-------------------READNEXT");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICSCITM.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_NORMAL, AICSCITM.RC);
            WS_REC_HAVE = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_REC_HAVE == 'N') {
                AICSCITM.RETURN_INFO = 'N';
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.REC_NOTFND, AICSCITM.RC);
            }
        } else {
            AICSCITM.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_FILE_ERR, AICSCITM.RC);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITAD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S100_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        CEP.TRC(SCCGWA, "----------------AIZPQITM");
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AICQITM.RC.RTNCODE);
        if (AICQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICSCITM.RC);
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
