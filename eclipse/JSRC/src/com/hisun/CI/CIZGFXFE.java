package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZGFXFE {
    brParm CITCREL_BR = new brParm();
    DBParm CITCREL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT = "CIX01";
    String WS_SER_STS = " ";
    char WS_CITCREL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRCREL CIRCREL = new CIRCREL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSIGFX CICSIGFX;
    public void MP(SCCGWA SCCGWA, CICSIGFX CICSIGFX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSIGFX = CICSIGFX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZGFXFE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_FEE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSIGFX.CARD_NO);
        if (CICSIGFX.CARD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CARD_NO_IS_NULL, CICSIGFX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CARD_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        CIRCREL.KEY.CARD_NO = CICSIGFX.CARD_NO;
        T000_STARTBR_CITCREL();
        if (pgmRtn) return;
        T000_READNEXT_CITCREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRCREL.STATUS);
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER1);
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER2);
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER5);
        while (WS_CITCREL_FLG != 'N') {
            if (CIRCREL.PREF_SER1 == '1') {
                CICSIGFX.ATM_TRA_FLG = 'Y';
            }
            if (CIRCREL.PREF_SER2 == '1') {
                CICSIGFX.ATM_DR_FLG = 'Y';
            }
            if (CIRCREL.PREF_SER5 == '1') {
                CICSIGFX.TRM_TCTD_FLG = 'Y';
            }
            T000_READNEXT_CITCREL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCREL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICSIGFX;
        SCCFMT.DATA_LEN = 31;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_CITCREL() throws IOException,SQLException,Exception {
        CITCREL_BR.rp = new DBParm();
        CITCREL_BR.rp.TableName = "CITCREL";
        CITCREL_BR.rp.where = "CARD_NO = :CIRCREL.KEY.CARD_NO";
        IBS.STARTBR(SCCGWA, CIRCREL, this, CITCREL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CITCREL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CITCREL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCREL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_CITCREL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCREL, this, CITCREL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CITCREL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CITCREL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCREL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITCREL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCREL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CITCREL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CITCREL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCREL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCREL_CD_NO_FIRST() throws IOException,SQLException,Exception {
        CITCREL_RD = new DBParm();
        CITCREL_RD.TableName = "CITCREL";
        CITCREL_RD.where = "CARD_NO = :CIRCREL.KEY.CARD_NO "
            + "AND ( PREF_SER1 = '1' "
            + "OR PREF_SER2 = '1' "
            + "OR PREF_SER5 = '1' )";
        CITCREL_RD.fst = true;
        IBS.READ(SCCGWA, CIRCREL, this, CITCREL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CITCREL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CITCREL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCREL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICSIGFX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICSIGFX=");
            CEP.TRC(SCCGWA, CICSIGFX);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
