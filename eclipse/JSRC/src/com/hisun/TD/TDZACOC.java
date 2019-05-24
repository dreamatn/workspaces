package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CIRACR;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class TDZACOC {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIX67";
    int K_MAX_ROW = 25;
    String K_MMO = "CI";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_NUM = 0;
    int WS_Z = 0;
    short WS_I = 0;
    short WS_T = 0;
    short WS_O = 0;
    short WS_VA_K = 0;
    short WS_RECORD_NUM = 0;
    short WS_CX = 0;
    short WS_PAGE_ROW = 0;
    char WS_ACR_STS = ' ';
    TDZACOC_WS_TEMP_DATA WS_TEMP_DATA = new TDZACOC_WS_TEMP_DATA();
    char WS_OCAC_FLG = ' ';
    char WS_ACR_FLG = ' ';
    String WS_FRM_APP = "TD";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    TDCOACOC TDCOACOC = new TDCOACOC();
    CIRACR CIRACR = new CIRACR();
    TDROCAC TDROCAC = new TDROCAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    TDCACOC TDCACOC;
    public void MP(SCCGWA SCCGWA, TDCACOC TDCACOC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCACOC = TDCACOC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZACOC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, TDCOACOC.OUTPUT_TITLE);
        IBS.init(SCCGWA, WS_TEMP_DATA);
        TDCACOC.RC.RC_MMO = K_MMO;
        TDCACOC.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_TDTOCAC();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACOC.DATA.FUNC);
        CEP.TRC(SCCGWA, TDCACOC.DATA.BR);
        CEP.TRC(SCCGWA, TDCACOC.DATA.OPDT);
        CEP.TRC(SCCGWA, TDCACOC.DATA.CLDT);
        CEP.TRC(SCCGWA, TDCACOC.DATA.FRM_APP);
        CEP.TRC(SCCGWA, TDCACOC.DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, TDCACOC.DATA.PAGE_NUM);
        if ((TDCACOC.DATA.FUNC != 'O' 
            && TDCACOC.DATA.FUNC != 'C')) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACOC.DATA.BR == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((TDCACOC.DATA.CITYP != '1' 
            && TDCACOC.DATA.CITYP != '2' 
            && TDCACOC.DATA.CITYP != '3' 
            && TDCACOC.DATA.CITYP != '4')) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INV_CI_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACOC.DATA.CLDT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INV_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACOC.DATA.OPDT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INV_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACOC.DATA.CLDT < TDCACOC.DATA.OPDT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CLDT_LESS_OPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!TDCACOC.DATA.FRM_APP.equalsIgnoreCase("TD")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INV_AC_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_TDTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROCAC);
        if (TDCACOC.DATA.FUNC == 'O') {
            TDROCAC.KEY.STS = 'N';
        } else {
            TDROCAC.KEY.STS = 'C';
        }
        TDROCAC.BR = TDCACOC.DATA.BR;
        TDROCAC.CRT_DATE = TDCACOC.DATA.OPDT;
        if (TDCACOC.DATA.CITYP == '4') {
            T000_STARTBR_TDTOCAC1();
            if (pgmRtn) return;
        } else {
            TDROCAC.CI_TYPE = TDCACOC.DATA.CITYP;
            T000_STARTBR_TDTOCAC();
            if (pgmRtn) return;
        }
        T000_READNEXT_TDTOCAC();
        if (pgmRtn) return;
        TDCOACOC.OUTPUT_TITLE.LAST_PAGE = 'N';
        if (TDCACOC.DATA.PAGE_ROW > K_MAX_ROW 
            || TDCACOC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = (short) K_MAX_ROW;
        } else {
            WS_PAGE_ROW = (short) TDCACOC.DATA.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        CEP.TRC(SCCGWA, TDCACOC.DATA.PAGE_NUM);
        if (TDCACOC.DATA.PAGE_NUM > 0) {
            WS_CX = (short) (( TDCACOC.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1);
            B020_20_CURR_PAGE();
            if (pgmRtn) return;
        } else {
            B020_10_TOTAL_PAGE();
            if (pgmRtn) return;
        }
    }
    public void B020_10_TOTAL_PAGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TOTAL-PAGE");
        while (WS_OCAC_FLG != 'N' 
            && TDROCAC.CRT_DATE <= TDCACOC.DATA.CLDT) {
            B020_TRAN_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, WS_TEMP_DATA);
            T000_READNEXT_TDTOCAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTOCAC();
        if (pgmRtn) return;
        B020_01_OUT_PAGE_TITLE();
        if (pgmRtn) return;
    }
    public void B020_20_CURR_PAGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CURR-PAGE");
        for (WS_T = 1; WS_O <= WS_PAGE_ROW 
            && WS_OCAC_FLG != 'N' 
            && TDROCAC.CRT_DATE <= TDCACOC.DATA.CLDT; WS_T += 1) {
            B020_TRAN_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, WS_TEMP_DATA);
            T000_READNEXT_TDTOCAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTOCAC();
        if (pgmRtn) return;
    }
    public void B020_TRAN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_O);
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
        CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CEP.TRC(SCCGWA, CIRACR.STS);
        CEP.TRC(SCCGWA, CIRACR.FRM_APP);
        CEP.TRC(SCCGWA, CIRACR.CCY);
        CEP.TRC(SCCGWA, WS_FRM_APP);
        if (TDCACOC.DATA.FUNC == 'O') {
            WS_TEMP_DATA.WS_OPEN_DT = TDROCAC.CRT_DATE;
            WS_TEMP_DATA.WS_OPEN_BR = TDROCAC.BR;
        } else {
            WS_TEMP_DATA.WS_CLOSE_DT = TDROCAC.CRT_DATE;
            WS_TEMP_DATA.WS_CLOSE_BR = TDROCAC.BR;
        }
        WS_TEMP_DATA.WS_AC_NO = TDROCAC.KEY.AC;
        WS_TEMP_DATA.WS_CCY = TDROCAC.CCY;
        WS_TEMP_DATA.WS_BICO_SING = TDROCAC.CCY_TYPE;
        WS_TEMP_DATA.WS_TLR_NO = TDROCAC.CRT_TLR;
        WS_TEMP_DATA.WS_AC_DT = TDROCAC.CRT_DATE;
        if (TDROCAC.KEY.AC == null) TDROCAC.KEY.AC = "";
        JIBS_tmp_int = TDROCAC.KEY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDROCAC.KEY.AC += " ";
        if (WS_TEMP_DATA.WS_NRA_NO == null) WS_TEMP_DATA.WS_NRA_NO = "";
        JIBS_tmp_int = WS_TEMP_DATA.WS_NRA_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_TEMP_DATA.WS_NRA_NO += " ";
        WS_TEMP_DATA.WS_NRA_NO = WS_TEMP_DATA.WS_NRA_NO.substring(0, 4 - 1) + TDROCAC.KEY.AC.substring(0, 22) + WS_TEMP_DATA.WS_NRA_NO.substring(4 + 22 - 1);
        WS_FRM_APP = TDCACOC.DATA.FRM_APP;
        WS_TEMP_DATA.WS_CON_STS = TDROCAC.KEY.STS;
        B040_READ_CITACR();
        if (pgmRtn) return;
