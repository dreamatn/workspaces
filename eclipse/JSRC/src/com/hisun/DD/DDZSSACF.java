package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSSACF {
    brParm DDTFRAC_BR = new brParm();
    int JIBS_tmp_int;
    brParm CITACR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_EOF_FRAC_FLG = ' ';
    char WS_ENDBR_FLG = ' ';
    DDZSSACF_WS_OUTPUT_DATA WS_OUTPUT_DATA = new DDZSSACF_WS_OUTPUT_DATA();
    char WS_ACR_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CICSSCH CICSSCH = new CICSSCH();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRFRAC DDRFRAC = new DDRFRAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSSACF DDCSSACF;
    public void MP(SCCGWA SCCGWA, DDCSSACF DDCSSACF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSSACF = DDCSSACF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSSACF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_EOF_FRAC_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSSACF.CI_NO);
        CEP.TRC(SCCGWA, DDCSSACF.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSSACF.ID_NO);
        if (DDCSSACF.CI_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CICSSCH);
            CICSSCH.FUNC = 'I';
            CICSSCH.INPUT_DATA.I_ID_TYPE = DDCSSACF.ID_TYPE;
            CICSSCH.INPUT_DATA.I_ID_NO = DDCSSACF.ID_NO;
            S000_CALL_CIZSSCH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSSCH.OUTPUT_DATA.O_CI_NO);
            WS_OUTPUT_DATA.WS_CI_NO = CICSSCH.OUTPUT_DATA.O_CI_NO;
        } else {
            WS_OUTPUT_DATA.WS_CI_NO = DDCSSACF.CI_NO;
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_NO);
        if (WS_OUTPUT_DATA.WS_CI_NO.trim().length() > 0) {
            B020_GET_FIST_SHOW_AC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_FIST_SHOW_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = WS_OUTPUT_DATA.WS_CI_NO;
        CIRACR.SHOW_FLG = 'Y';
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (WS_ACR_FLG != 'N') {
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.STS);
            if (CIRACR.STS != '1') {
                B030_GET_INF();
                if (pgmRtn) return;
                B040_CALL_ACTY_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B030_GET_INF() throws IOException,SQLException,Exception {
        WS_OUTPUT_DATA.WS_AC_NO = CIRACR.KEY.AGR_NO;
        WS_OUTPUT_DATA.WS_OPEN_BR = CIRACR.OPN_BR;
        WS_OUTPUT_DATA.WS_OPEN_DATE = CIRACR.OPEN_DT;
        WS_OUTPUT_DATA.WS_PRD_CODE = CIRACR.PROD_CD;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_NO);
    }
    public void B040_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = WS_OUTPUT_DATA.WS_AC_NO;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
            WS_OUTPUT_DATA.WS_AC_TYPE = '1';
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                if (DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD")) {
                    WS_OUTPUT_DATA.WS_AC_TYPE = '2';
                }
                if (DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DC")) {
                    WS_OUTPUT_DATA.WS_AC_TYPE = '3';
                }
            }
        }
        WS_OUTPUT_DATA.WS_FRRE_FLG = 'N';
        WS_OUTPUT_DATA.WS_FRAC_FLG = ' ';
        IBS.init(SCCGWA, DDRFRAC);
        DDRFRAC.KEY.CI_NO = WS_OUTPUT_DATA.WS_CI_NO;
        DDRFRAC.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
        T000_STARTBR_DDTFRAC();
        if (pgmRtn) return;
        T000_READNEXT_DDTFRAC();
        if (pgmRtn) return;
        if (WS_EOF_FRAC_FLG == 'Y') {
            B050_OUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_EOF_FRAC_FLG != 'Y') {
            WS_OUTPUT_DATA.WS_FRRE_FLG = 'Y';
            WS_OUTPUT_DATA.WS_FRAC_FLG = DDRFRAC.KEY.FLG;
            B050_OUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_DDTFRAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTFRAC();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DDTFRAC() throws IOException,SQLException,Exception {
        DDTFRAC_BR.rp = new DBParm();
        DDTFRAC_BR.rp.TableName = "DDTFRAC";
        DDTFRAC_BR.rp.where = "AC = :DDRFRAC.KEY.AC "
            + "AND CI_NO = :DDRFRAC.KEY.CI_NO";
        IBS.STARTBR(SCCGWA, DDRFRAC, this, DDTFRAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            if (SCCEXCP.MSG_TEXT.KEY_OR_OTHER == null) SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "";
            JIBS_tmp_int = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) SCCEXCP.MSG_TEXT.KEY_OR_OTHER += " ";
            if (DDRFRAC.KEY.CI_NO == null) DDRFRAC.KEY.CI_NO = "";
            JIBS_tmp_int = DDRFRAC.KEY.CI_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) DDRFRAC.KEY.CI_NO += " ";
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = DDRFRAC.KEY.CI_NO + SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(12);
            if (SCCEXCP.MSG_TEXT.KEY_OR_OTHER == null) SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "";
            JIBS_tmp_int = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) SCCEXCP.MSG_TEXT.KEY_OR_OTHER += " ";
            if (DDRFRAC.KEY.AC == null) DDRFRAC.KEY.AC = "";
            JIBS_tmp_int = DDRFRAC.KEY.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDRFRAC.KEY.AC += " ";
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(0, 13 - 1) + DDRFRAC.KEY.AC + SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(13 + DDRFRAC.KEY.AC.length() - 1);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTFRAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRFRAC, this, DDTFRAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FRAC_FLG = 'N';
        } else {
            WS_EOF_FRAC_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            if (SCCEXCP.MSG_TEXT.KEY_OR_OTHER == null) SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "";
            JIBS_tmp_int = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) SCCEXCP.MSG_TEXT.KEY_OR_OTHER += " ";
            if (DDRFRAC.KEY.CI_NO == null) DDRFRAC.KEY.CI_NO = "";
            JIBS_tmp_int = DDRFRAC.KEY.CI_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) DDRFRAC.KEY.CI_NO += " ";
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = DDRFRAC.KEY.CI_NO + SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(12);
            if (SCCEXCP.MSG_TEXT.KEY_OR_OTHER == null) SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "";
            JIBS_tmp_int = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) SCCEXCP.MSG_TEXT.KEY_OR_OTHER += " ";
            if (DDRFRAC.KEY.AC == null) DDRFRAC.KEY.AC = "";
            JIBS_tmp_int = DDRFRAC.KEY.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDRFRAC.KEY.AC += " ";
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(0, 13 - 1) + DDRFRAC.KEY.AC + SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(13 + DDRFRAC.KEY.AC.length() - 1);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTFRAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTFRAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "ENDBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            if (SCCEXCP.MSG_TEXT.KEY_OR_OTHER == null) SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "";
            JIBS_tmp_int = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) SCCEXCP.MSG_TEXT.KEY_OR_OTHER += " ";
            if (DDRFRAC.KEY.CI_NO == null) DDRFRAC.KEY.CI_NO = "";
            JIBS_tmp_int = DDRFRAC.KEY.CI_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) DDRFRAC.KEY.CI_NO += " ";
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = DDRFRAC.KEY.CI_NO + SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(12);
            if (SCCEXCP.MSG_TEXT.KEY_OR_OTHER == null) SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "";
            JIBS_tmp_int = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) SCCEXCP.MSG_TEXT.KEY_OR_OTHER += " ";
            if (DDRFRAC.KEY.AC == null) DDRFRAC.KEY.AC = "";
            JIBS_tmp_int = DDRFRAC.KEY.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDRFRAC.KEY.AC += " ";
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(0, 13 - 1) + DDRFRAC.KEY.AC + SCCEXCP.MSG_TEXT.KEY_OR_OTHER.substring(13 + DDRFRAC.KEY.AC.length() - 1);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 71;
        SCCMPAG.SCR_ROW_CNT = 25;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_OUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PRD_CODE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OPEN_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OPEN_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_FRRE_FLG);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_FRAC_FLG);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 71;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_CITACR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.where = "CI_NO = :CIRACR.CI_NO "
            + "AND SHOW_FLG = :CIRACR.SHOW_FLG "
            + "AND ( FRM_APP = 'DD' "
            + "OR FRM_APP = 'DC' )";
        CITACR_BR.rp.order = "AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI", CICSSCH, true);
        if (CICSSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
