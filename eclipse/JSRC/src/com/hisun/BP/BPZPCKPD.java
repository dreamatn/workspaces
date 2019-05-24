package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCKPD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    BPZPCKPD_WS_ATH_LVL WS_ATH_LVL = new BPZPCKPD_WS_ATH_LVL();
    BPZPCKPD_WS_RGN_NO WS_RGN_NO = new BPZPCKPD_WS_RGN_NO();
    int ACCOUT_NM = 0;
    char WS_TBL_PDLM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICMGRPM CICMGRPM = new CICMGRPM();
    CIRCGRP CIRCGRP = new CIRCGRP();
    SCCGWA SCCGWA;
    BPCPCKPD BPCPCKPD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPCKPD BPCPCKPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCKPD = BPCPCKPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCKPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCKPD.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCPCKPD.CI_NO);
        IBS.init(SCCGWA, BPCPQPRD);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, CICMGRPM);
        BPCPQPRD.PRDT_CODE = BPCPCKPD.PRDT_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CICCUST.DATA.CI_NO = BPCPCKPD.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (BPCPQPRD.CUS_PER_FLG != '0' 
            && CICCUST.O_DATA.O_CI_TYP == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.CUS_CANNOT_BUY, BPCPCKPD.RC);
        }
        if (BPCPQPRD.CUS_COM_FLG != '0' 
            && CICCUST.O_DATA.O_CI_TYP == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.CUS_CANNOT_BUY, BPCPCKPD.RC);
        }
        if (BPCPQPRD.CUS_FIN_FLG != '0' 
            && CICCUST.O_DATA.O_CI_TYP == '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.CUS_CANNOT_BUY, BPCPCKPD.RC);
        }
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_SCOPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_SCOPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP_SCOPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CHNL_SCOPE);
        if (BPCPQPRD.CUS_GROUP_SCOPE > '0') {
            IBS.init(SCCGWA, CICMGRPM);
            ACCOUT_NM = 0;
            CICMGRPM.INPUT_DATA.CI_NO = BPCPCKPD.CI_NO;
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP1);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP2);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP3);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP4);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP5);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP6);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP7);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP8);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP9);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_GROUP10);
            if (BPCPQPRD.CUS_GROUP1.trim().length() > 0) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP1;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                if (CICMGRPM.RETURN_INFO == 'N') {
                    CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP2;
                    CICMGRPM.FUNC = 'Q';
                    S000_CALL_CIZMGRPM();
                    if (pgmRtn) return;
                    S000_CALL_CIZSGRPM2();
                    if (pgmRtn) return;
                } else if (CICMGRPM.RETURN_INFO == 'F') {
                    ACCOUT_NM += 1;
                }
            } else {
                S000_CALL_CIZSGRPM2();
                if (pgmRtn) return;
                if (CICMGRPM.RETURN_INFO == 'F') {
                    ACCOUT_NM += 1;
                }
            }
            if ((BPCPQPRD.CUS_GROUP_SCOPE == '1' 
                && ACCOUT_NM == 0) 
                || (BPCPQPRD.CUS_GROUP_SCOPE == '2' 
                && ACCOUT_NM > 0)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.HAS_NOT_SUIT_GRPS, BPCPCKPD.RC);
            }
        }
        if (BPCPQPRD.CUS_COM_SCOPE > '0' 
            && CICCUST.O_DATA.O_CI_TYP == '2') {
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ORG_TYPE);
            if (CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP1) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP2) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP3) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP4) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP5) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP6) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP7) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP8) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP9) 
                || CICCUST.O_DATA.O_ORG_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_COM_TYP10)) {
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.HAS_NOT_SUIT_ORGTP, BPCPCKPD.RC);
            }
        }
        if (BPCPQPRD.CUS_FIN_SCOPE > '0' 
            && CICCUST.O_DATA.O_CI_TYP == '3') {
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_FIN_TYPE);
            if (CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP1) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP2) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP3) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP4) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP5) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP6) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP7) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP8) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP9) 
                || CICCUST.O_DATA.O_FIN_TYPE.equalsIgnoreCase(BPCPQPRD.CUS_FIN_TYP10)) {
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.HAS_NOT_SUIT_ORGTP, BPCPCKPD.RC);
            }
        }
        if (BPCPQPRD.CHNL_SCOPE > '0') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL1) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL2) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL3) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL4) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL5) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL6) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL7) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL8) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL9) 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(BPCPQPRD.CHNL10)) {
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.HAS_NOT_SUIT_CHNL, BPCPCKPD.RC);
            }
        }
        CEP.TRC(SCCGWA, "*** B200 EXIT ***");
        CEP.TRC(SCCGWA, BPCPCKPD.RC.RC_CODE);
    }
    public void S000_CALL_CIZSGRPM2() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP2.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP1.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP2;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM3();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM3();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM3() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP3.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP2.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP3;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM4();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM4();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM4() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP4.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP3.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP4;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM5();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM5();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM5() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP5.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP4.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP5;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM6();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM6();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM6() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP6.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP5.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP6;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM7();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM7();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM7() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP7.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP6.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP7;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM8();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM8();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM8() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP8.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP7.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP8;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM9();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM9();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM9() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP9.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP8.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP9;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
                S000_CALL_CIZSGRPM10();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        } else {
            S000_CALL_CIZSGRPM10();
            if (pgmRtn) return;
            if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_CIZSGRPM10() throws IOException,SQLException,Exception {
        if (BPCPQPRD.CUS_GROUP10.trim().length() > 0) {
            if ((CICMGRPM.RETURN_INFO == 'N' 
                    || BPCPQPRD.CUS_GROUP9.trim().length() == 0)) {
                CICMGRPM.INPUT_DATA.GRPS_NO = BPCPQPRD.CUS_GROUP10;
                CICMGRPM.FUNC = 'Q';
                S000_CALL_CIZMGRPM();
                if (pgmRtn) return;
            } else if (CICMGRPM.RETURN_INFO == 'F') {
                ACCOUT_NM += 1;
            }
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCKPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMGRPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMGRPM", CICMGRPM);
        CEP.TRC(SCCGWA, CICMGRPM.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCKPD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCKPD = ");
            CEP.TRC(SCCGWA, BPCPCKPD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
