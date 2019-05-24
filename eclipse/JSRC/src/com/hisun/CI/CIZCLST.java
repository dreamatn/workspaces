package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCLST {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    short WS_CTLW = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICSSCH CICSSCH = new CICSSCH();
    CICSLST CICSLST = new CICSLST();
    CIRID CIRID = new CIRID();
    CICPLST CICPLST = new CICPLST();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    String WS_LSTCD1 = " ";
    String WS_LSTCD2 = " ";
    String WS_LSTCD3 = " ";
    String WS_LSTCD4 = " ";
    String WS_LSTCD5 = " ";
    String WS_LSTCD6 = " ";
    String WS_LSTCD7 = " ";
    String WS_LSTCD8 = " ";
    String WS_LSTCD9 = " ";
    String WS_LSTCD10 = " ";
    String WS_LSTCD11 = " ";
    String WS_LSTCD12 = " ";
    String WS_LSTCD13 = " ";
    String WS_LSTCD14 = " ";
    String WS_LSTCD15 = " ";
    String WS_LSTCD16 = " ";
    String WS_LSTCD17 = " ";
    String WS_LSTCD18 = " ";
    String WS_LSTCD19 = " ";
    String WS_LSTCD20 = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCLST CICCLST;
    public void MP(SCCGWA SCCGWA, CICCLST CICCLST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCLST = CICCLST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZCLST return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_SEARCH_LIST();
        if (CICCLST.CTLW.CALL_TYP == '1') {
            B030_INNER_CALL_PROC();
        } else if (CICCLST.CTLW.CALL_TYP == '2') {
            B030_OTH_APP_CALL_PROC();
        } else {
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_SEARCH_LIST() throws IOException,SQLException,Exception {
        if (CICCLST.DATA.LIST_STSW == null) CICCLST.DATA.LIST_STSW = "";
        JIBS_tmp_int = CICCLST.DATA.LIST_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CICCLST.DATA.LIST_STSW += " ";
        JIBS_tmp_str[0] = "" + 0;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CICCLST.DATA.LIST_STSW = JIBS_tmp_str[0] + CICCLST.DATA.LIST_STSW.substring(30);
        CEP.TRC(SCCGWA, CICCLST.DATA.LIST_STSW);
        if (CICCLST.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICSLST);
            CICSLST.FUNC = 'O';
            CICSLST.DATA.CI_NO = CICCLST.DATA.CI_NO;
            S000_LINK_CIZSLST();
        } else {
            IBS.init(SCCGWA, CICSLST);
            CICSLST.FUNC = 'U';
            CICSLST.DATA.ID_TYPE = CICCLST.DATA.ID_TYPE;
            CICSLST.DATA.ID_NO = CICCLST.DATA.ID_NO;
            CICSLST.DATA.CI_CNM = CICCLST.DATA.CI_CNM;
            CICSLST.DATA.CI_ENM = CICCLST.DATA.CI_ENM;
            CEP.TRC(SCCGWA, CICCLST.DATA.CI_CNM);
            CEP.TRC(SCCGWA, CICCLST.DATA.CI_ENM);
            S000_LINK_CIZSLST();
        }
    }
    public void B030_INNER_CALL_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (CICSLST.RTN.MULTI_DATA[WS_I-1].LST_CD.trim().length() > 0) {
                CICCLST.DATA.LST_CD = CICSLST.RTN.MULTI_DATA[WS_I-1].LST_CD;
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CHECK_CI_IS_LST, 32);
            }
        }
    }
    public void B030_OTH_APP_CALL_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            IBS.init(SCCGWA, CICPLST);
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMR);
            if (CICSLST.RTN.MULTI_DATA[WS_I-1].LST_CD.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, CICSLST.RTN.MULTI_DATA[WS_I-1].LST_CD);
                BPRPRMT.KEY.TYP = "CILST";
                BPRPRMT.KEY.CD = CICSLST.RTN.MULTI_DATA[WS_I-1].LST_CD;
                CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
                BPCPRMR.DAT_PTR = BPRPRMT;
                S000_CALL_BPZPRMR();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPLST);
                CEP.TRC(SCCGWA, CICPLST.DATA_TXT.CTLW_POS);
                WS_CTLW = CICPLST.DATA_TXT.CTLW_POS;
                if (CICCLST.DATA.LIST_STSW == null) CICCLST.DATA.LIST_STSW = "";
                JIBS_tmp_int = CICCLST.DATA.LIST_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CICCLST.DATA.LIST_STSW += " ";
                CICCLST.DATA.LIST_STSW = CICCLST.DATA.LIST_STSW.substring(0, WS_CTLW - 1) + "1" + CICCLST.DATA.LIST_STSW.substring(WS_CTLW + 1 - 1);
                if (CICPLST.DATA_TXT.ATTR == '0') {
                    if (CICCLST.DATA.LIST_STSW == null) CICCLST.DATA.LIST_STSW = "";
                    JIBS_tmp_int = CICCLST.DATA.LIST_STSW.length();
                    for (int i=0;i<30-JIBS_tmp_int;i++) CICCLST.DATA.LIST_STSW += " ";
                    CICCLST.DATA.LIST_STSW = "1" + CICCLST.DATA.LIST_STSW.substring(1);
                    if (CICSLST.RTN.MULTI_DATA[WS_I-1].LST_FLG == '1') {
                        if (CICCLST.DATA.LIST_STSW == null) CICCLST.DATA.LIST_STSW = "";
                        JIBS_tmp_int = CICCLST.DATA.LIST_STSW.length();
                        for (int i=0;i<30-JIBS_tmp_int;i++) CICCLST.DATA.LIST_STSW += " ";
                        CICCLST.DATA.LIST_STSW = CICCLST.DATA.LIST_STSW.substring(0, 2 - 1) + "1" + CICCLST.DATA.LIST_STSW.substring(2 + 1 - 1);
                    }
                    if (CICSLST.RTN.MULTI_DATA[WS_I-1].LST_FLG == '2') {
                        if (CICCLST.DATA.LIST_STSW == null) CICCLST.DATA.LIST_STSW = "";
                        JIBS_tmp_int = CICCLST.DATA.LIST_STSW.length();
                        for (int i=0;i<30-JIBS_tmp_int;i++) CICCLST.DATA.LIST_STSW += " ";
                        CICCLST.DATA.LIST_STSW = CICCLST.DATA.LIST_STSW.substring(0, 3 - 1) + "1" + CICCLST.DATA.LIST_STSW.substring(3 + 1 - 1);
                    }
                }
                CEP.TRC(SCCGWA, CICCLST.DATA.LIST_STSW);
            }
        }
        CEP.TRC(SCCGWA, CICCLST.DATA.LIST_STSW);
    }
    public void S000_LINK_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI  ", CICSSCH);
    }
    public void S000_LINK_CIZSLST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-LST  ", CICSLST);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LST_CD_NOT_DEF);
            } else {
                CEP.ERR(SCCGWA, BPCPRMR.RC);
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICCLST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICCLST=");
            CEP.TRC(SCCGWA, CICCLST);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
