package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSPASS {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCHQP_RD;
    brParm DDTCHQP_BR = new brParm();
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "DD526";
    String K_AGT_TYP_PSW = "IBS022";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    char WS_AC_TYPE = ' ';
    char WS_SPASS_STS = ' ';
    DDZSPASS_WS_OUT_DATA WS_OUT_DATA = new DDZSPASS_WS_OUT_DATA();
    String WS_PL_AC_NO = " ";
    char WS_CHQP_STS = ' ';
    DDZSPASS_WS_SEQ WS_SEQ = new DDZSPASS_WS_SEQ();
    char WS_CHQP_FLG = ' ';
    char WS_UPDAE_FLG = ' ';
    char WS_STS_FLG = ' ';
    char WS_UPCI_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    CICMAGT CICMAGT = new CICMAGT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    DDCOPASS DDCOPASS = new DDCOPASS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRCHQP DDRCHQP = new DDRCHQP();
    DDRMST DDRMST = new DDRMST();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSPASS DDCSPASS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSPASS DDCSPASS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSPASS = DDCSPASS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSPASS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        B030_GET_AC_INF_PROC();
        B060_UPD_PASS_STS();
        B070_OUTPUT_DATA();
        B080_NFIN_TX_HIS_PROC();
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPASS.STS);
        if ((DDCSPASS.STS != '2' 
            && DDCSPASS.STS != '4')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PASS_STS_NOT_EXIST);
        }
        if (DDCSPASS.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        CEP.TRC(SCCGWA, DDCSPASS.PASS_NO);
        if (DDCSPASS.PASS_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PASS_NO_M_INPUT);
        }
        if (DDCSPASS.STA_DT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_DT_M_INPUT);
        }
        if (DDCSPASS.STS == '2') {
            if (DDCSPASS.STA_DT < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDT_M_GTEQ_ACDT);
            }
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = DDCSPASS.STA_DT;
        CEP.TRC(SCCGWA, DDCSPASS.STA_DT);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID);
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSPASS.AC_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSPASS.AC_NO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CUST_F_JUS_FF);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSPASS.AC_NO;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            T000_READ_DDTMST();
            if (DDRMST.AC_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
            }
            if (DDRMST.AC_STS == 'D') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_L_HANGED);
            }
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_L_HANGED);
        }
    }
    public void B030_GET_AC_STD_PROC() throws IOException,SQLException,Exception {
        if (DDCSPASS.AC_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, DDCSPASS.AC_NO);
            IBS.init(SCCGWA, DCCUSPAC);
            DCCUSPAC.FUNC.AC = DDCSPASS.AC_NO;
            S000_CALL_DCZUSPAC();
            CEP.TRC(SCCGWA, DCCUSPAC.OUTPUT.AC_TYPE);
            if (DCCUSPAC.OUTPUT.AC_TYPE == '0') {
                WS_AC_TYPE = '1';
            }
            if (DCCUSPAC.OUTPUT.AC_TYPE == '1' 
                || DCCUSPAC.OUTPUT.AC_TYPE == '2') {
                WS_AC_TYPE = '0';
            }
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DDCSPASS.AC_NO;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        }
    }
    public void B040_GET_AC_INF_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCPACTY.OUTPUT.AC_TYPE == 'V') {
            IBS.init(SCCGWA, DCRIAMST);
            DCRIAMST.KEY.VIA_AC = DDCSPASS.AC_NO;
            CEP.TRC(SCCGWA, DCRIAMST.AC_STS);
            CEP.TRC(SCCGWA, DCRIAMST.PRD_TYPE);
            if (!DCRIAMST.PRD_TYPE.equalsIgnoreCase("07")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VA_PRD_TYPE_INVALID;
                S000_ERR_MSG_PROC();
            }
            if (DCRIAMST.AC_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_CLOSE;
                S000_ERR_MSG_PROC();
            }
        } else {
            IBS.init(SCCGWA, DDRMST);
            CEP.TRC(SCCGWA, DCCUSPAC.OUTPUT.STD_AC);
            CEP.TRC(SCCGWA, DCCUSPAC.OUTPUT.AC_TYPE);
            T000_READ_DDTMST();
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B060_UPD_PASS_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "000000000000");
        CEP.TRC(SCCGWA, DDCSPASS.STS);
        if (DDCSPASS.STS == '2') {
            CEP.TRC(SCCGWA, "22222222");
            T000_READ_DDTCHQP();
            if (WS_CHQP_FLG == 'Y') {
                CEP.TRC(SCCGWA, DDRCHQP.KEY.STS);
                if (DDRCHQP.KEY.STS == '2') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_PASS_HAS_EXIST);
                } else {
                    if (DDRCHQP.KEY.STR_DT == DDCSPASS.STA_DT) {
                        WS_OUT_DATA.WS_PASS_NO = DDRCHQP.KEY.PASS_NO;
                        WS_OUT_DATA.WS_TR_DT = DDRCHQP.KEY.STR_DT;
                        T000_READ_UPDATE_DDTCHQP2();
                        DDRCHQP.KEY.STS = '2';
                        DDRCHQP.STOP_DT = 0;
                        DDRCHQP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DDRCHQP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_REWRITE_DDTCHQP_PROC();
                    } else {
                        B061_ADD_DDTCHQP();
                    }
                }
            } else {
                B061_ADD_DDTCHQP();
            }
        } else if (DDCSPASS.STS == '4') {
            CEP.TRC(SCCGWA, "44444444");
            T000_READ_UPDATE_DDTCHQP();
            if (WS_CHQP_FLG == 'N') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_PASS_NOT_EXIST);
            }
            WS_OUT_DATA.WS_PASS_NO = DDRCHQP.KEY.PASS_NO;
            WS_OUT_DATA.WS_TR_DT = DDRCHQP.KEY.STR_DT;
            DDRCHQP.STOP_DT = SCCGWA.COMM_AREA.AC_DATE;
            DDRCHQP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCHQP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCHQP_PROC();
            T000_READ_UPDATE_DDTCHQP1();
            WS_CHQP_STS = '4';
            DDRCHQP.KEY.STS = '4';
            T000_REWRITE_DDTCHQP_PROC1();
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PASS_STS_NOT_EXIST);
        }
        B060_ADD_PWS_TOCI_PROC();
    }
    public void B061_ADD_DDTCHQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        DDRCHQP.KEY.PASS_NO = DDCSPASS.PASS_NO;
        WS_CHQP_STS = '2';
        DDRCHQP.KEY.STS = '2';
        DDRCHQP.KEY.STR_DT = DDCSPASS.STA_DT;
        DDRCHQP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCHQP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTCHQP_PROC();
    }
    public void B061_CHECK_CHOP_STS() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCHQP_PROC();
        T000_READNEXT_DDTCHQP_PROC();
        while (WS_CHQP_FLG != 'N') {
            DDRCHQP.KEY.STS = '5';
            T000_REWRITE_DDTCHQP_PROC();
            T000_READNEXT_DDTCHQP_PROC();
        }
        T000_ENDBR_DDTCHQP_PROC();
    }
    public void B050_UPD_PASS_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        if (WS_UPDAE_FLG == 'M') {
            CEP.TRC(SCCGWA, "MORE");
            T000_STARTBR_DDTCHQP_PROC();
            T000_READNEXT_DDTCHQP_PROC();
            if (WS_CHQP_FLG == 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_PASS_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
            while (WS_CHQP_FLG != 'N') {
                CEP.TRC(SCCGWA, "WS-CHQP-FOUND");
                CEP.TRC(SCCGWA, DDCSPASS.STS);
                CEP.TRC(SCCGWA, DDRCHQP.KEY.STS);
                CEP.TRC(SCCGWA, WS_STS_FLG);
                if (WS_STS_FLG == 'N') {
                    CEP.TRC(SCCGWA, "WUYIPING");
                    if ((DDCSPASS.STS == '2' 
                        && (DDRCHQP.KEY.STS == '3' 
                        || DDRCHQP.KEY.STS == '0')) 
                        || (DDCSPASS.STS == '5' 
                        && DDRCHQP.KEY.STS == '4')) {
                        CEP.TRC(SCCGWA, "BBBBBBBBBBBBBBBBBBBBBBBBBBB");
                        DDRCHQP.KEY.STR_DT = DDCSPASS.TR_DT;
                        DDRCHQP.STOP_DT = 0;
                    } else {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_NOT_UPDATE;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (WS_STS_FLG == 'A') {
                    if ((DDCSPASS.STS == '3' 
                        && (DDRCHQP.KEY.STS == '1' 
                        || DDRCHQP.KEY.STS == '2' 
                        || DDRCHQP.KEY.STS == '5' 
                        || DDRCHQP.KEY.STS == '0')) 
                        || (DDCSPASS.STS == '4' 
                        && DDRCHQP.KEY.STS != '4')) {
                        CEP.TRC(SCCGWA, "111111111111");
                        DDRCHQP.STOP_DT = DDCSPASS.TR_DT;
                    } else {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_NOT_UPDATE;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (DDRCHQP.KEY.STS != '0') {
                    DDRCHQP.KEY.STS = DDCSPASS.STS;
                    T000_REWRITE_DDTCHQP_PROC();
                }
                T000_READNEXT_DDTCHQP_PROC();
            }
            T000_ENDBR_DDTCHQP_PROC();
        }
        if (WS_UPDAE_FLG == 'O') {
            if (DDCSPASS.STS == '1') {
                B055_CHK_CI_CHQP_PROC();
            }
            T000_READ_UPDATE_DDTCHQP();
            WS_SPASS_STS = DDRCHQP.KEY.STS;
            DDRCHQP.KEY.PASS_NO = DDCSPASS.PASS_NO;
            if (DDCSPASS.STS == '1') {
                DDRCHQP.KEY.STR_DT = DDCSPASS.TR_DT;
                DDRCHQP.STOP_DT = 0;
                DDRCHQP.KEY.STS = DDCSPASS.STS;
            }
            if (DDCSPASS.STS == '0') {
                DDRCHQP.STOP_DT = DDCSPASS.TR_DT;
                DDRCHQP.KEY.STS = DDCSPASS.STS;
            }
            CEP.TRC(SCCGWA, WS_CHQP_FLG);
            CEP.TRC(SCCGWA, DDCSPASS.STS);
            if (WS_CHQP_FLG == 'N') {
                CEP.TRC(SCCGWA, "18824184791");
                if (DDCSPASS.STS == '1') {
                    T000_WRITE_DDTCHQP_PROC();
                }
                if (DDCSPASS.STS == '0') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_PASS_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_CHQP_FLG == 'Y') {
                CEP.TRC(SCCGWA, "3333333333333333333");
                CEP.TRC(SCCGWA, DDRCHQP.KEY.STS);
                if (((WS_SPASS_STS == '1' 
                    || WS_SPASS_STS == '2' 
                    || WS_SPASS_STS == '5') 
                    && DDCSPASS.STS == '0') 
                    || (WS_SPASS_STS == '0' 
                    && DDCSPASS.STS == '1')) {
                    T000_REWRITE_DDTCHQP_PROC();
                    if (DDCSPASS.STS == '0') {
                        B055_CHK_CI_CHQP_PROC();
                    }
                } else {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_NOT_UPDATE;
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, WS_UPCI_FLG);
            CEP.TRC(SCCGWA, DDCSPASS.STS);
            if (WS_UPCI_FLG == 'N' 
                && WS_UPDAE_FLG == 'O') {
                B060_ADD_PWS_TOCI_PROC();
            }
        }
    }
    public void B055_CHK_CI_CHQP_PROC() throws IOException,SQLException,Exception {
        if (DDCSPASS.STS == '0' 
            || DDCSPASS.STS == '1') {
            T000_READ_DDTCHQP();
        }
    }
    public void B060_ADD_PWS_TOCI_PROC() throws IOException,SQLException,Exception {
        if (DDCSPASS.STS == '2') {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'A';
            CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            CICMAGT.DATA.AGT_TYP = "3001011";
            CICMAGT.DATA.ENTY_TYP = '1';
            CICMAGT.DATA.ENTY_NO = DDCSPASS.AC_NO;
            CICMAGT.DATA.FRM_APP = "DD";
            CICMAGT.DATA.AGT_LVL = 'A';
            CICMAGT.DATA.EFF_DATE = DDCSPASS.STA_DT;
            CICMAGT.DATA.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.AGT_STS = 'N';
            S000_CALL_CIZMAGT();
        }
        if (DDCSPASS.STS == '4') {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'D';
            CICMAGT.DATA.AGT_TYP = "3001011";
            CICMAGT.DATA.ENTY_TYP = '1';
            CICMAGT.DATA.ENTY_NO = DDCSPASS.AC_NO;
            S000_CALL_CIZMAGT();
        }
    }
    public void B070_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPASS);
        DDCOPASS.AC_NO = WS_PL_AC_NO;
        DDCOPASS.STS = DDCSPASS.STS;
        DDCOPASS.TR_DT = DDCSPASS.TR_DT;
        CEP.TRC(SCCGWA, WS_OUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOPASS;
        SCCFMT.DATA_LEN = 51;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B080_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (DDCSPASS.STS == '4') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        BPCPNHIS.INFO.AC = DDCSPASS.AC_NO;
        BPCPNHIS.INFO.TX_TOOL = DDCSPASS.AC_NO;
        BPCPNHIS.INFO.FMT_ID = "DDCSPASS";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (DDCSPASS.STS == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P10A";
        }
        if (DDCSPASS.STS == '4') {
            BPCPNHIS.INFO.TX_TYP_CD = "P100";
        }
        S000_CALL_BPZPNHIS();
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        DDTMST_RD.col = "CUS_AC,AC_STS";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_DDTCHQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        DDRCHQP.KEY.PASS_NO = DDCSPASS.PASS_NO;
        CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.STR_DT);
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        DDTCHQP_RD.where = "AC = :DDRCHQP.KEY.AC "
            + "AND STS = '2' "
            + "AND PASS_NO = :DDRCHQP.KEY.PASS_NO";
        DDTCHQP_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQP, this, DDTCHQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHQP_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCHQP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_READ_UPDATE_DDTCHQP1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        DDRCHQP.KEY.PASS_NO = WS_OUT_DATA.WS_PASS_NO;
        DDRCHQP.KEY.STR_DT = WS_OUT_DATA.WS_TR_DT;
        DDRCHQP.KEY.STS = '2';
        CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.STR_DT);
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        DDTCHQP_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQP, DDTCHQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHQP_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCHQP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_READ_UPDATE_DDTCHQP2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        DDRCHQP.KEY.PASS_NO = WS_OUT_DATA.WS_PASS_NO;
        DDRCHQP.KEY.STR_DT = WS_OUT_DATA.WS_TR_DT;
        DDRCHQP.KEY.STS = '4';
        CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.STR_DT);
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        DDTCHQP_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQP, DDTCHQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHQP_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCHQP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCHQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        DDRCHQP.KEY.PASS_NO = DDCSPASS.PASS_NO;
        CEP.TRC(SCCGWA, DDRCHQP.KEY.PASS_NO);
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        DDTCHQP_RD.where = "AC = :DDRCHQP.KEY.AC "
            + "AND PASS_NO = :DDRCHQP.KEY.PASS_NO";
        DDTCHQP_RD.fst = true;
        DDTCHQP_RD.order = "STR_DT DESC";
        IBS.READ(SCCGWA, DDRCHQP, this, DDTCHQP_RD);
        CEP.TRC(SCCGWA, DDRCHQP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHQP_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCHQP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_DDTCHQP_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDTCHQP_BR.rp = new DBParm();
        DDTCHQP_BR.rp.TableName = "DDTCHQP";
        IBS.STARTBR(SCCGWA, DDRCHQP, DDTCHQP_BR);
    }
    public void T000_STARTBR_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        DDRCHQP.KEY.STR_DT = DDCSPASS.STA_DT;
        DDTCHQP_BR.rp = new DBParm();
        DDTCHQP_BR.rp.TableName = "DDTCHQP";
        DDTCHQP_BR.rp.where = "AC = :DDRCHQP.KEY.AC "
            + "AND STR_DT > :DDRCHQP.KEY.STR_DT";
        DDTCHQP_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.STR_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else {
            WS_CHQP_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQP_BR);
    }
    public void T000_WRITE_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        DDRCHQP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCHQP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCHQP.KEY.STR_DT = DDCSPASS.STA_DT;
        DDRCHQP.KEY.STS = WS_CHQP_STS;
        DDRCHQP.KEY.AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, DDRCHQP.KEY.STS);
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        DDTCHQP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCHQP, DDTCHQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_PASS_HAS_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        DDRCHQP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        IBS.REWRITE(SCCGWA, DDRCHQP, DDTCHQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_DDTCHQP_PROC1() throws IOException,SQLException,Exception {
        DDRCHQP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCHQP_RD = new DBParm();
        DDTCHQP_RD.TableName = "DDTCHQP";
        IBS.REWRITE(SCCGWA, DDRCHQP, DDTCHQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
