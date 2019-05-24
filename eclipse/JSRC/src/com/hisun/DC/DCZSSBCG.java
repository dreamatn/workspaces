package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSSBCG {
    brParm DCTDCSCC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short MAX_ROW = 20;
    DCZSSBCG_WS_VARIABLES WS_VARIABLES = new DCZSSBCG_WS_VARIABLES();
    DCZSSBCG_WS_OUTPUT WS_OUTPUT = new DCZSSBCG_WS_OUTPUT();
    DCZSSBCG_WS_COND_FLG WS_COND_FLG = new DCZSSBCG_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRDCSCC DCRDCSCC = new DCRDCSCC();
    DCRDCSCC_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DCRDCSCC_WS_SQL_VARIABLES();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSSBCG DCCSSBCG;
    public void MP(SCCGWA SCCGWA, DCCSSBCG DCCSSBCG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSSBCG = DCCSSBCG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSSBCG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        WS_COND_FLG.TBL_FLAG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSSBCG.CARD_NO);
        CEP.TRC(SCCGWA, DCCSSBCG.SC_NO);
        CEP.TRC(SCCGWA, DCCSSBCG.START_DATE);
        CEP.TRC(SCCGWA, DCCSSBCG.END_DATE);
        if (DCCSSBCG.CARD_NO.trim().length() == 0 
            && DCCSSBCG.SC_NO.trim().length() == 0 
            && DCCSSBCG.START_DATE == 0 
            && DCCSSBCG.END_DATE == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_QUERY_INF_MISSING;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_INFO() throws IOException,SQLException,Exception {
        if (DCCSSBCG.CARD_NO.trim().length() > 0) {
            WS_SQL_VARIABLES.CARD_NO = DCCSSBCG.CARD_NO;
            IBS.init(SCCGWA, DCRDCSCC);
            T000_STARTBR_DCTDCSCC1();
            if (pgmRtn) return;
            T100_READNEXT_DCTDCSCC();
            if (pgmRtn) return;
            C000_OUTPUT_INI();
            if (pgmRtn) return;
            while (WS_COND_FLG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                C100_OUTPUT_LIST();
                if (pgmRtn) return;
                T100_READNEXT_DCTDCSCC();
                if (pgmRtn) return;
            }
            T200_ENDBR_DCTDCSCC();
            if (pgmRtn) return;
        }
        if (DCCSSBCG.SC_NO.trim().length() > 0) {
            WS_SQL_VARIABLES.SC_NO = DCCSSBCG.SC_NO;
            IBS.init(SCCGWA, DCRDCSCC);
            T000_STARTBR_DCTDCSCC2();
            if (pgmRtn) return;
            T100_READNEXT_DCTDCSCC();
            if (pgmRtn) return;
            C000_OUTPUT_INI();
            if (pgmRtn) return;
            while (WS_COND_FLG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                C100_OUTPUT_LIST();
                if (pgmRtn) return;
                T100_READNEXT_DCTDCSCC();
                if (pgmRtn) return;
            }
            T200_ENDBR_DCTDCSCC();
            if (pgmRtn) return;
        }
        if (DCCSSBCG.START_DATE != 0 
            && DCCSSBCG.END_DATE != 0) {
            WS_SQL_VARIABLES.START_DATE = DCCSSBCG.START_DATE;
            WS_SQL_VARIABLES.END_DATE = DCCSSBCG.END_DATE;
            IBS.init(SCCGWA, DCRDCSCC);
            T000_STARTBR_DCTDCSCC3();
            if (pgmRtn) return;
            T100_READNEXT_DCTDCSCC();
            if (pgmRtn) return;
            C000_OUTPUT_INI();
            if (pgmRtn) return;
            while (WS_COND_FLG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                C100_OUTPUT_LIST();
                if (pgmRtn) return;
                T100_READNEXT_DCTDCSCC();
                if (pgmRtn) return;
            }
            T200_ENDBR_DCTDCSCC();
            if (pgmRtn) return;
        }
    }
    public void C000_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 777;
        SCCMPAG.SCR_ROW_CNT = MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.O_CARD_NO = DCRDCSCC.CARD_NO;
        WS_OUTPUT.O_SC_NO = DCRDCSCC.SOCIAL_NO;
        WS_OUTPUT.O_SC_CARD_NO = DCRDCSCC.SOCIAL_CARD_NO;
        WS_OUTPUT.O_E_CARD_NO = DCRDCSCC.E_CARD_NO;
        WS_OUTPUT.O_SEX_OLD = DCRDCSCC.SEX_OLD;
        WS_OUTPUT.O_SEX = DCRDCSCC.SEX;
        WS_OUTPUT.O_CI_NM_OLD = DCRDCSCC.CI_NM_OLD;
        WS_OUTPUT.O_CI_NM = DCRDCSCC.CI_NM;
        WS_OUTPUT.O_ID_TYP_OLD = DCRDCSCC.ID_TYP_OLD;
        WS_OUTPUT.O_ID_TYP = DCRDCSCC.ID_TYP;
        WS_OUTPUT.O_ID_NO_OLD = DCRDCSCC.ID_NO_OLD;
        WS_OUTPUT.O_ID_NO = DCRDCSCC.ID_NO;
        WS_OUTPUT.O_TXN_DT = DCRDCSCC.KEY.TXN_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 777;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTDCSCC1() throws IOException,SQLException,Exception {
        DCTDCSCC_BR.rp = new DBParm();
        DCTDCSCC_BR.rp.TableName = "DCTDCSCC";
        DCTDCSCC_BR.rp.where = "CARD_NO = :WS_SQL_VARIABLES.CARD_NO";
        IBS.STARTBR(SCCGWA, DCRDCSCC, this, DCTDCSCC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCSCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTDCSCC2() throws IOException,SQLException,Exception {
        DCTDCSCC_BR.rp = new DBParm();
        DCTDCSCC_BR.rp.TableName = "DCTDCSCC";
        DCTDCSCC_BR.rp.where = "SOCIAL_NO = :WS_SQL_VARIABLES.SC_NO";
        IBS.STARTBR(SCCGWA, DCRDCSCC, this, DCTDCSCC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCSCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTDCSCC3() throws IOException,SQLException,Exception {
        DCTDCSCC_BR.rp = new DBParm();
        DCTDCSCC_BR.rp.TableName = "DCTDCSCC";
        DCTDCSCC_BR.rp.where = "TXN_DT >= :WS_SQL_VARIABLES.START_DATE "
            + "AND TXN_DT <= :WS_SQL_VARIABLES.END_DATE";
        IBS.STARTBR(SCCGWA, DCRDCSCC, this, DCTDCSCC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCSCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T100_READNEXT_DCTDCSCC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCSCC, this, DCTDCSCC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCSCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T200_ENDBR_DCTDCSCC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDCSCC_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
