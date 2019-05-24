package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.BP.*;
import com.hisun.TD.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSXXT {
    DBParm CITBAS_RD;
    DBParm CITACAC_RD;
    DBParm CITACRL_RD;
    brParm CITACR_BR = new brParm();
    DBParm CITACR_RD;
    DBParm CITAGT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    String K_XXT_AGT_TYP = "IBS007";
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    CIRAGT CIRAGT = new CIRAGT();
    TDCACE TDCACE = new TDCACE();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICFA01 CICFA01 = new CICFA01();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSXXT CICSXXT;
    public void MP(SCCGWA SCCGWA, CICSXXT CICSXXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSXXT = CICSXXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSXXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSXXT.RC);
        IBS.init(SCCGWA, CICFA01);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_COMPUTE_OUTPUT_ROW();
        if (pgmRtn) return;
        B030_INQUIRE_XXT_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSXXT.DATA.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSXXT.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSXXT.DATA.PAGE_ROW);
        if (CICSXXT.DATA.PAGE_ROW > K_MAX_ROW) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICSXXT.DATA.PAGE_ROW;
        }
    }
    public void B020_COMPUTE_OUTPUT_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSXXT.DATA.PAGE_NUM);
        WS_MIN_ROW = ( CICSXXT.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_MIN_ROW);
        WS_MAX_ROW = CICSXXT.DATA.PAGE_NUM * WS_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_MAX_ROW);
        WS_CURRENT_ROW = 0;
        WS_I = 0;
    }
    public void B030_INQUIRE_XXT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICSXXT.DATA.CI_NO;
        T000_STARTBR_CITACR_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CARD_INF_NOTFND);
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.REL_AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "06";
            T000_READ_CITACRL_BY_REL_AC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                    && WS_CURRENT_ROW <= WS_MAX_ROW) {
                    R000_TRANS_DATA_TO_OUTPUT();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        WS_I = WS_I + 1;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CICFA01.FMT.DATA[WS_I-1].AGR_NO = CIRACR.KEY.AGR_NO;
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
        T000_READ_CITACAC_DEFAULT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
        CEP.TRC(SCCGWA, CIRACAC.CCY);
        CEP.TRC(SCCGWA, CIRACAC.CR_FLG);
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = CIRACR.KEY.AGR_NO;
        DDCIQBAL.DATA.CCY = CIRACAC.CCY;
        DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        CICFA01.FMT.DATA[WS_I-1].CUR_BAL = DDCIQBAL.DATA.CURR_BAL;
        CICFA01.FMT.DATA[WS_I-1].CUR_ABAL = DDCIQBAL.DATA.AVL_BAL;
        CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACRL.KEY.AC_NO;
        T000_READ_CITACAC_DEFAULT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
        CEP.TRC(SCCGWA, CIRACAC.AGR_SEQ);
        CEP.TRC(SCCGWA, CIRACAC.BV_NO);
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = CIRACRL.KEY.AC_NO;
        TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
        TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BAL);
        CEP.TRC(SCCGWA, TDCACE.DATA[1-1].KY_BAL);
        CICFA01.FMT.DATA[WS_I-1].XXT_BAL = TDCACE.DATA[1-1].BAL;
        CICFA01.FMT.DATA[WS_I-1].XXT_ABAL = TDCACE.DATA[1-1].KY_BAL;
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_TYP = CIRACR.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CIRACR.KEY.AGR_NO;
        CIRAGT.AGT_TYP = K_XXT_AGT_TYP;
        CIRAGT.AGT_STS = 'N';
        T000_READ_CITAGT_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFA01.FMT.DATA[WS_I-1].SIG_FLG = '0';
        } else {
            CICFA01.FMT.DATA[WS_I-1].SIG_FLG = '1';
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CIRACRL.KEY.AC_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CICFA01.FMT.DATA[WS_I-1].SIG_DT = CIRACR.OPEN_DT;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CURRENT_ROW);
        CICFA01.FMT.CI_NO = CICSXXT.DATA.CI_NO;
        CICFA01.FMT.TOTAL_NUM = WS_CURRENT_ROW;
        WS_RECORD_NUM = WS_CURRENT_ROW % WS_PAGE_ROW;
        CICFA01.FMT.TOTAL_PAGE = (int) ((WS_CURRENT_ROW - WS_RECORD_NUM) / WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            CICFA01.FMT.TOTAL_PAGE = CICFA01.FMT.TOTAL_PAGE + 1;
        }
        CICFA01.FMT.CURR_PAGE = CICSXXT.DATA.PAGE_NUM;
        CICFA01.FMT.PAGE_ROW = WS_I;
        if (CICFA01.FMT.CURR_PAGE >= CICFA01.FMT.TOTAL_PAGE 
            || CICFA01.FMT.TOTAL_PAGE == 0) {
            CICFA01.FMT.LAST_PAGE = 'Y';
        } else {
            CICFA01.FMT.LAST_PAGE = 'N';
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIA01";
        SCCFMT.DATA_PTR = CICFA01;
        SCCFMT.DATA_LEN = 3187;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_READ_CITACRL_BY_REL_AC() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "REL_AC_NO , AC_REL";
        CITACRL_RD.where = "REL_STS = '0'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "SHOW_FLG = 'Y' "
            + "AND STS = '0' "
            + "AND ENTY_TYP = '2'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITAGT_FIRST() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.eqWhere = "ENTY_NO , ENTY_TYP , AGT_TYP , AGT_STS";
        CITAGT_RD.fst = true;
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
