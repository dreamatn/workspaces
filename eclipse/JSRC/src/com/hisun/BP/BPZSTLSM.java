package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLSM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP559";
    String K_OUTPUT_FMT_2 = "BPX01";
    String K_OUTPUT_FMT_3 = "BP560";
    String K_OUTPUT_FMT_4 = "BP563";
    String K_OUTPUT_FMT_5 = "BP569";
    String K_PGM_NAME = "BPZSTLSM";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_F_PRIV_RVK = "BP-F-PRIV-RVK       ";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_QUERY_TLT_HOLIDAY = "BP-P-QUERY-TLR-HOL  ";
    String CPN_R_TLTS_MAINTAIN = "BP-R-TLR-HOL-M      ";
    String CPN_U_CASH_BV_CHK = "BP-U-CASH-BV-CHK    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "TLR STATUS MAINTAIN";
    String K_CPY_BPRTDTL = "BPRTDTL";
    String WS_ERR_MSG = " ";
    char WS_OPT = ' ';
    char WS_RUN_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCRTLTS BPCRTLTS = new BPCRTLTS();
    BPCFPRRV BPCFPRRV = new BPCFPRRV();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTDTL BPRTDTL = new BPRTDTL();
    BPRTDTL BPROTDTL = new BPRTDTL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOTLSM BPCOTLSM = new BPCOTLSM();
    BPCPQTLH BPCPQTLH = new BPCPQTLH();
    BPCOOTLS BPCOOTLS = new BPCOOTLS();
    BPCUTCVC BPCUTCVC = new BPCUTCVC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCO4919_OPT_4919 BPCO4919_OPT_4919 = new BPCO4919_OPT_4919();
    BPCO4910_OPT_4910 BPCO4910_OPT_4910 = new BPCO4910_OPT_4910();
    SCCGWA SCCGWA;
    BPCSTLSM BPCSTLSM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTLSM BPCSTLSM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLSM = BPCSTLSM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        A000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTLSM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPRTDTL);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.init(SCCGWA, BPCRTLTS);
    }
    public void A000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTLSM.CHK_FLG);
        if (BPCSTLSM.CHK_FLG == 'Y') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B010_COMMON_CHECK_CN();
            } else {
                B010_COMMON_CHECK();
                B020_SPECIAL_PROCESS();
            }
            if (BPCSTLSM.OPT == 'T' 
                || BPCSTLSM.OPT == 'O') {
                R000_CHECK_HOL_ON();
            }
        } else {
            B000_MAIN_PROC();
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_COMMON_CHECK_CN();
        } else {
            B010_COMMON_CHECK();
            B020_SPECIAL_PROCESS();
        }
        CEP.TRC(SCCGWA, "============================");
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        if (BPCSTLSM.OPT == 'T') {
            R000_CHECK_HOL_ON();
            B045_MOVE_PROCESS();
        } else if (BPCSTLSM.OPT == 'I') {
            B050_MOVE_IN_PROCESS();
        } else if (BPCSTLSM.OPT == 'O') {
            R000_CHECK_HOL_ON();
            B040_MOVE_OUT_PROCESS();
        } else if (BPCSTLSM.OPT == 'H'
            || BPCSTLSM.OPT == 'Q'
            || BPCSTLSM.OPT == 'M'
            || BPCSTLSM.OPT == 'C'
            || BPCSTLSM.OPT == 'A') {
            B031_UPDATE_TLTS_PROCESS();
        } else {
            B030_UPDATE_TLT_PROCESS();
            B040_DELETE_TDTL_PROCESS();
        }
        B040_OUTPUT_PROCESS();
    }
    public void B010_COMMON_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        CEP.TRC(SCCGWA, BPCSTLSM.OPT_T);
        if (BPCSTLSM.OPT == 'D'
            || BPCSTLSM.OPT == 'H'
            || BPCSTLSM.OPT == 'I'
            || BPCSTLSM.OPT == 'L'
            || BPCSTLSM.OPT == 'N'
            || BPCSTLSM.OPT == 'O'
            || BPCSTLSM.OPT == 'Q'
            || BPCSTLSM.OPT == 'M'
            || BPCSTLSM.OPT == 'A'
            || BPCSTLSM.OPT == 'C'
            || BPCSTLSM.OPT_T == 'P') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        BPCRTLTM.INFO.FUNC = 'Q';
        BPRTLT.KEY.TLR = BPCSTLSM.TLR;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLTM();
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.IN_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.OUT_BR);
        if (BPCSTLSM.OPT != 'I' 
            && BPCSTLSM.OPT != 'O' 
            && BPCSTLSM.OPT != 'T') {
            if (BPCSTLSM.OPT_T != 'P') {
                if (BPCSTLSM.TLR_BR != BPRTLT.TLR_BR) {
                    CEP.TRC(SCCGWA, "071101");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (BPCSTLSM.OPT == 'I') {
                CEP.TRC(SCCGWA, "071102");
                if (BPCSTLSM.IN_BR == BPRTLT.TLR_BR) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                }
            } else {
                CEP.TRC(SCCGWA, "071103");
                if (BPCSTLSM.OUT_BR != BPRTLT.TLR_BR) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_DT);
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        WS_OPT = BPCSTLSM.OPT;
        if ((WS_OPT != 'Q' 
            && WS_OPT != 'H' 
            && WS_OPT != 'M' 
            && WS_OPT != 'C' 
            && WS_OPT != 'A' 
            && WS_OPT != 'T' 
            && WS_OPT != 'S' 
            && WS_OPT != 'R' 
            && WS_OPT != 'N' 
            && WS_OPT != 'L' 
            && WS_OPT != 'P')) {
            if (BPRTLT.SIGN_STS == 'O' 
                || BPRTLT.SIGN_STS == 'T') {
                CEP.TRC(SCCGWA, "1111111");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'H' 
            || BPCSTLSM.OPT == 'O') {
            if (BPRTLT.TLR_STS != 'N') {
                CEP.TRC(SCCGWA, "2222222");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'I') {
            if (BPRTLT.TLR_STS != 'O') {
                CEP.TRC(SCCGWA, "3333333");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'I') {
            if (BPCSTLSM.IN_BR_EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IN_EFF_DT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'O') {
            if (BPRTLT.TLR_STS != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'O') {
            if (BPCSTLSM.OUT_BR_EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OUT_EFF_DT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'D') {
            if (!(BPRTLT.TLR_STS == 'N' 
                || BPRTLT.TLR_STS == 'H')) {
                CEP.TRC(SCCGWA, "44444444444");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'N') {
            if (!(BPRTLT.TLR_STS == 'D' 
                || BPRTLT.TLR_STS == 'H')) {
                CEP.TRC(SCCGWA, "555555");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'L') {
            if (BPRTLT.TLR_STS == 'L') {
                CEP.TRC(SCCGWA, "66666666");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCPRGST);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.IN_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.OUT_BR);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCSTLSM.OPT == 'I') {
            BPCPRGST.BR2 = BPCSTLSM.IN_BR;
        } else if (BPCSTLSM.OPT == 'O') {
            BPCPRGST.BR2 = BPCSTLSM.OUT_BR;
        } else {
            BPCPRGST.BR2 = BPCSTLSM.TLR_BR;
        }
        CEP.TRC(SCCGWA, BPCPRGST.BR1);
        CEP.TRC(SCCGWA, BPCPRGST.BR2);
        if (BPCPRGST.BR1 != BPCPRGST.BR2) {
            S000_CALL_BPZPRGST();
            if (BPCPRGST.FLAG != 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_RELATED_BRANCH;
                S000_ERR_MSG_PROC();
            }
            if (BPCPRGST.LVL_RELATION == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LVL_RELATION_LOW;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_COMMON_CHECK_CN() throws IOException,SQLException,Exception {
        if (BPCSTLSM.OPT == 'D'
            || BPCSTLSM.OPT == 'H'
            || BPCSTLSM.OPT == 'I'
            || BPCSTLSM.OPT == 'L'
            || BPCSTLSM.OPT == 'N'
            || BPCSTLSM.OPT == 'O'
            || BPCSTLSM.OPT == 'Q'
            || BPCSTLSM.OPT == 'M'
            || BPCSTLSM.OPT == 'A'
            || BPCSTLSM.OPT == 'C'
            || BPCSTLSM.OPT == 'T'
            || BPCSTLSM.OPT == 'S'
            || BPCSTLSM.OPT == 'R'
            || BPCSTLSM.OPT_T == 'P') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPCRTLTM.INFO.FUNC = 'Q';
        BPRTLT.KEY.TLR = BPCSTLSM.TLR;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLTM();
        if (BPCSTLSM.OPT == 'D'
            || BPCSTLSM.OPT == 'I'
            || BPCSTLSM.OPT == 'L'
            || BPCSTLSM.OPT == 'N'
            || BPCSTLSM.OPT == 'O'
            || BPCSTLSM.OPT == 'T' 
                && BPCSTLSM.OPT_T != 'P'
            || BPCSTLSM.OPT == 'S'
            || BPCSTLSM.OPT == 'R') {
            CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
            CEP.TRC(SCCGWA, BPRTLT.EFF_DT);
            if (SCCGWA.COMM_AREA.AC_DATE >= BPRTLT.EFF_DT 
                && BPRTLT.TLR_TYP != 'S') {
                if (BPRTLT.SIGN_STS != 'F') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TLR_ERR);
                }
            }
        } else {
        }
        if (BPCSTLSM.OPT_T == 'P') {
            if (BPCSTLSM.BEGIN_DT == SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
                CEP.TRC(SCCGWA, BPRTLT.EFF_DT);
                if (SCCGWA.COMM_AREA.AC_DATE >= BPRTLT.EFF_DT) {
                    if (BPRTLT.SIGN_STS != 'F') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TLR_ERR);
                    }
                }
            }
        }
        if (BPCSTLSM.OPT == 'H') {
            if (BPCSTLSM.BEGIN_DT == SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
                CEP.TRC(SCCGWA, BPRTLT.EFF_DT);
                if (SCCGWA.COMM_AREA.AC_DATE >= BPRTLT.EFF_DT) {
                    if (BPRTLT.SIGN_STS != 'F') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TLR_ERR);
                    }
                }
            }
        }
        if (BPCSTLSM.OPT == 'A') {
            if (BPCSTLSM.END_DT == SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
                CEP.TRC(SCCGWA, BPRTLT.EFF_DT);
                if (SCCGWA.COMM_AREA.AC_DATE >= BPRTLT.EFF_DT) {
                    if (BPRTLT.SIGN_STS != 'F') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TLR_ERR);
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.IN_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.OUT_BR);
        if (BPCSTLSM.OPT != 'T') {
            if (BPCSTLSM.TLR_BR != BPRTLT.TLR_BR) {
                CEP.TRC(SCCGWA, "071101");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (BPCSTLSM.IN_BR == BPRTLT.TLR_BR) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSM.OUT_BR != BPRTLT.TLR_BR) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_DT);
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        WS_OPT = BPCSTLSM.OPT;
        if ((WS_OPT != 'Q' 
            && WS_OPT != 'H' 
            && WS_OPT != 'M' 
            && WS_OPT != 'C' 
            && WS_OPT != 'A' 
            && WS_OPT != 'T' 
            && WS_OPT != 'S' 
            && WS_OPT != 'R' 
            && WS_OPT != 'N' 
            && WS_OPT != 'L' 
            && WS_OPT != 'P')) {
            if (BPRTLT.SIGN_STS == 'O' 
                || BPRTLT.SIGN_STS == 'T') {
                CEP.TRC(SCCGWA, "1111111");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USER_NOT_SIGN_OFF;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'H' 
            || BPCSTLSM.OPT == 'T') {
            if (BPRTLT.TLR_STS != 'N') {
                CEP.TRC(SCCGWA, "2222222");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'I') {
            if (BPRTLT.TLR_STS != 'O') {
                CEP.TRC(SCCGWA, "3333333");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'I') {
            if (BPCSTLSM.IN_BR_EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IN_EFF_DT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'O') {
            if (BPRTLT.TLR_STS != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'O') {
            if (BPCSTLSM.OUT_BR_EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OUT_EFF_DT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'D') {
            if (!(BPRTLT.TLR_STS == 'N' 
                || BPRTLT.TLR_STS == 'H')) {
                CEP.TRC(SCCGWA, "44444444444");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'N') {
            if (BPRTLT.TLR_STS != 'L') {
                CEP.TRC(SCCGWA, "555555");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'L') {
            if (BPRTLT.TLR_STS == 'L') {
                CEP.TRC(SCCGWA, "66666666");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'S') {
            if (BPRTLT.TLR_STS != 'N') {
                CEP.TRC(SCCGWA, "STOP USING THIS TLR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.OPT == 'R') {
            if (BPRTLT.TLR_STS != 'S') {
                CEP.TRC(SCCGWA, "RECOVERY THIS TLR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCPRGST);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.IN_BR);
        CEP.TRC(SCCGWA, BPCSTLSM.OUT_BR);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCSTLSM.OPT == 'T'
            || BPCSTLSM.OPT == 'P') {
            BPCPRGST.BR2 = BPCSTLSM.OUT_BR;
        } else {
            BPCPRGST.BR2 = BPCSTLSM.TLR_BR;
        }
        CEP.TRC(SCCGWA, BPCPRGST.BR1);
        CEP.TRC(SCCGWA, BPCPRGST.BR2);
        if (BPCSTLSM.OPT == 'T' 
            || BPCSTLSM.OPT == 'L' 
            || BPCSTLSM.OPT == 'S' 
            || BPCSTLSM.OPT == 'N' 
            || BPCSTLSM.OPT == 'R') {
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '2';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '3';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '0';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '1';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
        }
        if (BPCSTLSM.OPT == 'H') {
            if (BPCSTLSM.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSM.END_DT < BPCSTLSM.BEGIN_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPCSTLSM.TLR);
        BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
        BPCRTLTS.INFO.FUNC = 'R';
        BPRTDTL.KEY.TYPE = 'H';
        S001_CALL_BPZRTLTS();
        if (BPCSTLSM.OPT == 'M' 
            || BPCSTLSM.OPT == 'C' 
            || BPCSTLSM.OPT == 'A' 
            || BPCSTLSM.OPT == 'H') {
            if (BPCSTLSM.OPT == 'H') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST);
                }
            }
            T000_CHECK_HOL_INFO();
        }
        if (BPCSTLSM.OPT == 'T' 
            && BPCSTLSM.OPT_T == 'P') {
            if (BPCRTLTS.RETURN_INFO == 'F') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_H_RECORD);
            }
        }
    }
    public void B020_SPECIAL_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSTLSM.OPT == 'O') {
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '2';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '3';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '0';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCUTCVC);
                BPCUTCVC.TLR = BPCSTLSM.TLR;
                BPCUTCVC.CASH_BV_FLG = '1';
                BPCUTCVC.DEL_FLG = 'Y';
                S000_CALL_BPZUTCVC();
            }
        }
        if (BPCSTLSM.OPT == 'H') {
            if (BPCSTLSM.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSM.END_DT < BPCSTLSM.BEGIN_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_UPDATE_TLT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCSTLSM.TLR;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLTM();
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        R000_TRANS_DATA_PARAMETER();
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        BPCRTLTM.INFO.FUNC = 'M';
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        S000_CALL_BPZRTLTM();
    }
    public void B031_UPDATE_TLTS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQTLH);
        BPCPQTLH.INFO.TLR = BPCSTLSM.TLR;
        BPCPQTLH.INFO.TYPE = 'H';
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.TYPE);
        S000_CALL_BPZPQTLH();
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.BEGIN_DT);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.BEGIN_TIME);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.END_DT);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.END_TIME);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.SIGN_FLG);
        if (BPCPQTLH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
                S000_ERR_MSG_PROC();
            }
        }
        BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
        BPCRTLTS.INFO.FUNC = 'R';
        BPRTDTL.KEY.TYPE = 'H';
        S000_CALL_BPZRTLTS();
        if (BPCSTLSM.OPT != 'Q') {
            if (BPCSTLSM.OPT == 'H') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    BPCRTLTS.INFO.FUNC = 'C';
                } else {
                    BPCRTLTS.INFO.FUNC = 'M';
                }
                BPRTDTL.KEY.TYPE = 'H';
                R000_TRANS_HOLIDAY_DATA();
            } else if (BPCSTLSM.OPT == 'M') {
                BPCRTLTS.INFO.FUNC = 'M';
                R000_TRANS_HOLIDAY_DATA();
            } else if (BPCSTLSM.OPT == 'A') {
                if (BPCSTLSM.END_DT == SCCGWA.COMM_AREA.AC_DATE) {
                    BPCRTLTS.INFO.FUNC = 'D';
                } else {
                    BPCRTLTS.INFO.FUNC = 'M';
                    R000_TRANS_HOLIDAY_DATA();
                }
            } else if (BPCSTLSM.OPT == 'C') {
                BPCRTLTS.INFO.FUNC = 'D';
            }
            S000_CALL_BPZRTLTS();
        }
        CEP.TRC(SCCGWA, BPCSTLSM.SIGN_FLG);
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        B030_UPDATE_TLT_PROCESS();
    }
    public void B040_DELETE_TDTL_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTLSM.TLR);
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        if (BPCSTLSM.OPT == 'R') {
            IBS.init(SCCGWA, BPCRTLTS);
            IBS.init(SCCGWA, BPRTDTL);
            BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
            BPRTDTL.KEY.TYPE = 'A';
            BPCRTLTS.INFO.FUNC = 'R';
            S001_CALL_BPZRTLTS();
            CEP.TRC(SCCGWA, BPCRTLTS.RETURN_INFO);
            if (BPCRTLTS.RETURN_INFO == 'F') {
                BPCRTLTS.INFO.FUNC = 'D';
                BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
                BPRTDTL.KEY.TYPE = 'A';
                S001_CALL_BPZRTLTS();
            }
        }
        if (BPCSTLSM.OPT == 'L') {
            IBS.init(SCCGWA, BPCRTLTS);
            IBS.init(SCCGWA, BPRTDTL);
            BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
            BPRTDTL.KEY.TYPE = 'A';
            BPCRTLTS.INFO.FUNC = 'R';
            S001_CALL_BPZRTLTS();
            CEP.TRC(SCCGWA, BPCRTLTS.RETURN_INFO);
            if (BPCRTLTS.RETURN_INFO == 'F') {
                BPCRTLTS.INFO.FUNC = 'D';
                BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
                BPRTDTL.KEY.TYPE = 'A';
                S001_CALL_BPZRTLTS();
            }
            IBS.init(SCCGWA, BPCRTLTS);
            IBS.init(SCCGWA, BPRTDTL);
            BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
            BPRTDTL.KEY.TYPE = 'H';
            BPCRTLTS.INFO.FUNC = 'R';
            S001_CALL_BPZRTLTS();
            CEP.TRC(SCCGWA, BPCRTLTS.RETURN_INFO);
            if (BPCRTLTS.RETURN_INFO == 'F') {
                BPCRTLTS.INFO.FUNC = 'D';
                BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
                BPRTDTL.KEY.TYPE = 'H';
                S001_CALL_BPZRTLTS();
            }
            IBS.init(SCCGWA, BPCRTLTS);
            IBS.init(SCCGWA, BPRTDTL);
            BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
            BPRTDTL.KEY.TYPE = 'T';
            BPCRTLTS.INFO.FUNC = 'R';
            S001_CALL_BPZRTLTS();
            CEP.TRC(SCCGWA, BPCRTLTS.RETURN_INFO);
            if (BPCRTLTS.RETURN_INFO == 'F' 
                && BPRTDTL.BEGIN_DT != 0) {
                BPCRTLTS.INFO.FUNC = 'D';
                BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
                BPRTDTL.KEY.TYPE = 'T';
                S001_CALL_BPZRTLTS();
            }
        }
    }
    public void R000_CHECK_HOL_ON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQTLH);
        BPCPQTLH.INFO.TLR = BPCSTLSM.TLR;
        BPCPQTLH.INFO.TYPE = 'H';
        S000_CALL_BPZPQTLH();
        if (BPCPQTLH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "CCC");
            if (BPCPQTLH.INFO.BEGIN_DT < BPCPQTLH.INFO.END_DT) {
                if ((BPCPQTLH.INFO.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE 
                    && SCCGWA.COMM_AREA.AC_DATE < BPCPQTLH.INFO.END_DT)) {
                    if (BPCPQTLH.INFO.SIGN_FLG != 'Y') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_IN_HOLIDAY;
                        S000_ERR_MSG_PROC();
                    }
                }
            } else {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
            }
        }
    }
    public void B045_MOVE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQTLH);
        BPCPQTLH.INFO.TLR = BPCSTLSM.TLR;
        BPCPQTLH.INFO.TYPE = BPCSTLSM.OPT;
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.TYPE);
        S000_CALL_BPZPQTLH();
        if (BPCPQTLH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPRTDTL);
        IBS.init(SCCGWA, BPCRTLTS);
        CEP.TRC(SCCGWA, BPCSTLSM.TLR);
        BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
        BPRTDTL.KEY.TYPE = 'T';
        BPCRTLTS.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTS();
        IBS.CLONE(SCCGWA, BPRTDTL, BPROTDTL);
        BPRTDTL.KEY.TYPE = 'T';
        BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
        BPRTDTL.OUT_BR = BPCSTLSM.OUT_BR;
        BPRTDTL.IN_BR = BPCSTLSM.IN_BR;
        BPRTDTL.OUT_EFF_DATE = BPCSTLSM.OUT_BR_EFF_DT;
        BPRTDTL.IN_EFF_DATE = BPCSTLSM.IN_BR_EFF_DT;
        if (BPCSTLSM.OPT_T == 'P') {
            BPRTDTL.BEGIN_DT = BPCSTLSM.BEGIN_DT;
            BPRTDTL.BEGIN_TIME = BPCSTLSM.BEGIN_TIME;
            BPRTDTL.END_DT = BPCSTLSM.END_DT;
            BPRTDTL.END_TIME = BPCSTLSM.END_TIME;
        }
        if (BPCSTLSM.OPT == 'T') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.TRC(SCCGWA, "CREATE");
                BPCRTLTS.INFO.FUNC = 'C';
            } else {
                CEP.TRC(SCCGWA, "MODIFY");
                BPCRTLTS.INFO.FUNC = 'M';
            }
        }
        if (BPCSTLSM.OPT == 'T' 
            && BPCSTLSM.OPT_T == 'P') {
            if (BPCSTLSM.TMP_FUND == 'A') {
                CEP.TRC(SCCGWA, "CREATE-T");
                BPCRTLTS.INFO.FUNC = 'C';
            }
            if (BPCSTLSM.TMP_FUND == 'U') {
                CEP.TRC(SCCGWA, "MODIFY-T");
                BPCRTLTS.INFO.FUNC = 'M';
            }
        }
        S000_CALL_BPZRTLTS();
        B030_UPDATE_TLT_PROCESS();
        B045_01_HISTORY_RECORD();
    }
    public void B045_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTDTL;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID_LEN = 93;
        if (BPCRTLTS.INFO.FUNC == 'C') {
            CEP.TRC(SCCGWA, "HISTORY FOR ADD");
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPRTDTL;
        }
        if (BPCRTLTS.INFO.FUNC == 'M') {
            CEP.TRC(SCCGWA, "HISTORY FOR MOD");
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROTDTL;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRTDTL;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        S000_CALL_BPZPNHIS();
        CEP.TRC(SCCGWA, "HISTORY SUCCESS");
    }
    public void B040_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSTLSM.OPT == 'H') {
            IBS.init(SCCGWA, BPCOTLSM);
            BPCOTLSM.OPT = BPCSTLSM.OPT;
            BPCOTLSM.TLR_BR = BPCSTLSM.TLR_BR;
            BPCOTLSM.TLR = BPCSTLSM.TLR;
            BPCOTLSM.BEGIN_DT = BPCSTLSM.BEGIN_DT;
            BPCOTLSM.BEGIN_TIME = BPCSTLSM.BEGIN_TIME;
            BPCOTLSM.END_DT = BPCSTLSM.END_DT;
            BPCOTLSM.END_TIME = BPCSTLSM.END_TIME;
            BPCOTLSM.SIGN_FLG = BPCSTLSM.SIGN_FLG;
            if (BPCSTLSM.FMT_ID.trim().length() > 0) {
                SCCFMT.FMTID = BPCSTLSM.FMT_ID;
            } else {
                SCCFMT.FMTID = K_OUTPUT_FMT_3;
            }
            SCCFMT.DATA_PTR = BPCOTLSM;
            SCCFMT.DATA_LEN = 73;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSTLSM.OPT == 'Q'
            || BPCSTLSM.OPT == 'M'
            || BPCSTLSM.OPT == 'C'
            || BPCSTLSM.OPT == 'A') {
            CEP.TRC(SCCGWA, "AAAAAAAA");
            CEP.TRC(SCCGWA, BPCSTLSM.OPT);
            CEP.TRC(SCCGWA, BPCSTLSM.TLR_BR);
            CEP.TRC(SCCGWA, BPCPQTLH.INFO.BEGIN_DT);
            CEP.TRC(SCCGWA, BPCPQTLH.INFO.BEGIN_TIME);
            CEP.TRC(SCCGWA, BPCPQTLH.INFO.END_DT);
            CEP.TRC(SCCGWA, BPCPQTLH.INFO.END_TIME);
            CEP.TRC(SCCGWA, BPCPQTLH.INFO.SIGN_FLG);
            IBS.init(SCCGWA, BPCOTLSM);
            BPCOTLSM.OPT = BPCSTLSM.OPT;
            BPCOTLSM.TLR_BR = BPCSTLSM.TLR_BR;
            BPCOTLSM.TLR = BPCSTLSM.TLR;
            BPCOTLSM.BEGIN_DT = BPCPQTLH.INFO.BEGIN_DT;
            BPCOTLSM.BEGIN_TIME = BPCPQTLH.INFO.BEGIN_TIME;
            BPCOTLSM.END_DT = BPCPQTLH.INFO.END_DT;
            BPCOTLSM.END_TIME = BPCPQTLH.INFO.END_TIME;
            BPCOTLSM.SIGN_FLG = BPCPQTLH.INFO.SIGN_FLG;
            if (BPCSTLSM.FMT_ID.trim().length() > 0) {
                SCCFMT.FMTID = BPCSTLSM.FMT_ID;
            } else {
                SCCFMT.FMTID = K_OUTPUT_FMT_2;
            }
            SCCFMT.DATA_PTR = BPCOTLSM;
            SCCFMT.DATA_LEN = 73;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSTLSM.OPT == 'O') {
            IBS.init(SCCGWA, BPCOOTLS);
            BPCOOTLS.OPT = BPCSTLSM.OPT;
            BPCOOTLS.TLR_BR = BPCSTLSM.TLR_BR;
            BPCOOTLS.TLR = BPCSTLSM.TLR;
            BPCOOTLS.OUT_BR = BPCSTLSM.OUT_BR;
            BPCOOTLS.RUN_FLG = WS_RUN_FLG;
            SCCFMT.FMTID = K_OUTPUT_FMT;
            SCCFMT.DATA_PTR = BPCOOTLS;
            SCCFMT.DATA_LEN = 44;
            IBS.FMT(SCCGWA, SCCFMT);
            CEP.TRC(SCCGWA, BPCOOTLS);
        } else if (BPCSTLSM.OPT == 'I') {
            IBS.init(SCCGWA, BPCOOTLS);
            BPCOOTLS.OPT = BPCSTLSM.OPT;
            BPCOOTLS.TLR_BR = BPCSTLSM.TLR_BR;
            BPCOOTLS.TLR = BPCSTLSM.TLR;
            BPCOOTLS.IN_BR = BPCSTLSM.IN_BR;
            BPCOOTLS.RUN_FLG = WS_RUN_FLG;
            CEP.TRC(SCCGWA, BPCOOTLS);
            SCCFMT.FMTID = K_OUTPUT_FMT;
            SCCFMT.DATA_PTR = BPCOOTLS;
            SCCFMT.DATA_LEN = 44;
            IBS.FMT(SCCGWA, SCCFMT);
            CEP.TRC(SCCGWA, BPCOOTLS);
        } else if (BPCSTLSM.OPT == 'T') {
            if (BPCSTLSM.OPT_T != 'P') {
                IBS.init(SCCGWA, BPCO4910_OPT_4910);
                BPCO4910_OPT_4910.TLR = BPCSTLSM.TLR;
                BPCO4910_OPT_4910.TLR_NM = BPCSTLSM.TLR_NM;
                BPCO4910_OPT_4910.OPT = BPCSTLSM.OPT;
                BPCO4910_OPT_4910.TLR_BR = BPCSTLSM.TLR_BR;
                BPCO4910_OPT_4910.TLR_STS = 'T';
                BPCO4910_OPT_4910.IN_BR = BPCSTLSM.IN_BR;
                BPCO4910_OPT_4910.OUT_BR = BPCSTLSM.OUT_BR;
                CEP.TRC(SCCGWA, BPCO4910_OPT_4910);
                SCCFMT.FMTID = K_OUTPUT_FMT;
                SCCFMT.DATA_PTR = BPCO4910_OPT_4910;
                SCCFMT.DATA_LEN = 0;
                IBS.FMT(SCCGWA, SCCFMT);
            } else {
                IBS.init(SCCGWA, BPCO4919_OPT_4919);
                BPCO4919_OPT_4919.TLR = BPRTLT.KEY.TLR;
                BPCO4919_OPT_4919.OUT_BR = BPRTLT.TLR_BR;
                BPCO4919_OPT_4919.TLR_NM = BPRTLT.TLR_CN_NM;
                BPCO4919_OPT_4919.IN_BR = BPRTDTL.IN_BR;
                BPCO4919_OPT_4919.TLR_LVL = BPRTLT.TLR_LVL;
                BPCO4919_OPT_4919.BEGIN_DT = BPRTDTL.BEGIN_DT;
                BPCO4919_OPT_4919.END_DT = BPRTDTL.END_DT;
                BPCO4919_OPT_4919.BEGIN_TM = BPRTDTL.BEGIN_TIME;
                BPCO4919_OPT_4919.END_TM = BPRTDTL.END_TIME;
                IBS.init(SCCGWA, SCCFMT);
                SCCFMT.FMTID = K_OUTPUT_FMT;
                SCCFMT.DATA_PTR = BPCO4919_OPT_4919;
                SCCFMT.DATA_LEN = 0;
                IBS.FMT(SCCGWA, SCCFMT);
            }
        } else {
            IBS.init(SCCGWA, BPCOOTLS);
            BPCOOTLS.OPT = BPCSTLSM.OPT;
            BPCOOTLS.TLR_BR = BPCSTLSM.TLR_BR;
            BPCOOTLS.TLR = BPCSTLSM.TLR;
            SCCFMT.FMTID = K_OUTPUT_FMT;
            SCCFMT.DATA_PTR = BPCOOTLS;
            SCCFMT.DATA_LEN = 44;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRTLT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRTLT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (BPCSTLSM.OPT == 'H') {
            if (SCCGWA.COMM_AREA.AC_DATE >= BPCSTLSM.BEGIN_DT) {
                BPRTLT.TLR_STS = 'H';
            }
        } else if (BPCSTLSM.OPT == 'A') {
            if (SCCGWA.COMM_AREA.AC_DATE >= BPCSTLSM.END_DT) {
                BPRTLT.TLR_STS = 'N';
            }
        } else if (BPCSTLSM.OPT == 'O') {
            BPRTLT.TLR_LVL = '0';
            BPRTLT.TX_LVL = '0';
            BPRTLT.ATH_LVL = '0';
            BPRTLT.TMP_TX_LVL = '0';
            BPRTLT.TMP_ATH_LVL = '0';
            BPRTLT.ATH_RGN = '0';
            BPRTLT.CRO_BR_SIGN = '0';
            BPRTLT.TLR_STSW = "000000000001";
            BPRTLT.TLR_STS = 'O';
        } else if (BPCSTLSM.OPT == 'I') {
            BPRTLT.TLR_STS = 'N';
            BPRTLT.TLR_BR = BPCSTLSM.IN_BR;
            BPRTLT.TLR_LVL = '0';
            BPRTLT.TX_LVL = '0';
            BPRTLT.ATH_LVL = '0';
            BPRTLT.TMP_TX_LVL = '0';
            BPRTLT.TMP_ATH_LVL = '0';
            BPRTLT.ATH_RGN = '0';
            BPRTLT.CRO_BR_SIGN = '0';
            BPRTLT.TLR_STSW = "000000000001";
        } else if (BPCSTLSM.OPT == 'T') {
            BPRTLT.TLR_STS = 'N';
            if (BPCSTLSM.OPT_T != 'P') {
                BPRTLT.TLR_BR = BPCSTLSM.IN_BR;
            }
        } else if (BPCSTLSM.OPT == 'S') {
            BPRTLT.TLR_STS = 'S';
        } else if (BPCSTLSM.OPT == 'R') {
            BPRTLT.TLR_STS = 'N';
        } else if (BPCSTLSM.OPT == 'D') {
            BPRTLT.TLR_STS = 'D';
        } else if (BPCSTLSM.OPT == 'N') {
            BPRTLT.TLR_STS = 'N';
        } else if (BPCSTLSM.OPT == 'L') {
            BPRTLT.TLR_STS = 'L';
        } else {
        }
    }
    public void B050_MOVE_IN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTLSM.IN_BR_EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        B030_UPDATE_TLT_PROCESS();
    }
    public void B041_UPDATE_TDTL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQTLH);
        BPCPQTLH.INFO.TLR = BPCSTLSM.TLR;
        BPCPQTLH.INFO.TYPE = BPCSTLSM.OPT;
        S000_CALL_BPZPQTLH();
        CEP.TRC(SCCGWA, BPCPQTLH.RC);
        if (BPCPQTLH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AAA");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.TRC(SCCGWA, "BBB");
                IBS.init(SCCGWA, BPCRTLTS);
                IBS.init(SCCGWA, BPRTDTL);
                BPCRTLTS.INFO.FUNC = 'C';
                R000_TRS_MOVE_IN_OUT_DATA();
                BPCRTLTS.INFO.POINTER = BPRTDTL;
                BPCRTLTS.INFO.LEN = 93;
                S000_CALL_BPZRTLTS();
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "CCC");
            IBS.init(SCCGWA, BPCRTLTS);
            IBS.init(SCCGWA, BPRTDTL);
            BPCRTLTS.INFO.FUNC = 'R';
            BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
            BPRTDTL.KEY.TYPE = BPCSTLSM.OPT;
            BPCRTLTS.INFO.POINTER = BPRTDTL;
            BPCRTLTS.INFO.LEN = 93;
            S000_CALL_BPZRTLTS();
            BPCRTLTS.INFO.FUNC = 'M';
            R000_TRS_MOVE_IN_OUT_DATA();
            BPCRTLTS.INFO.POINTER = BPRTDTL;
            BPCRTLTS.INFO.LEN = 93;
            S000_CALL_BPZRTLTS();
        }
    }
    public void B040_MOVE_OUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPCRTLTM.INFO.FUNC = 'Q';
        BPRTLT.KEY.TLR = BPCSTLSM.TLR;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLTM();
        if (BPRTLT.TLR_STS != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
        }
        B030_UPDATE_TLT_PROCESS();
    }
    public void R000_TRS_MOVE_IN_OUT_DATA() throws IOException,SQLException,Exception {
        BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
        BPRTDTL.KEY.TYPE = BPCSTLSM.OPT;
        if (BPCSTLSM.OPT == 'O') {
            BPRTDTL.OUT_BR = BPCSTLSM.OUT_BR;
            BPRTDTL.OUT_EFF_DATE = BPCSTLSM.OUT_BR_EFF_DT;
        } else {
            BPRTDTL.IN_BR = BPCSTLSM.IN_BR;
            BPRTDTL.IN_EFF_DATE = BPCSTLSM.IN_BR_EFF_DT;
        }
        BPRTDTL.RUN_FLG = WS_RUN_FLG;
        CEP.TRC(SCCGWA, BPRTDTL.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRTDTL.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTDTL.OUT_BR);
        CEP.TRC(SCCGWA, BPRTDTL.OUT_EFF_DATE);
        CEP.TRC(SCCGWA, BPRTDTL.IN_BR);
        CEP.TRC(SCCGWA, BPRTDTL.IN_EFF_DATE);
        CEP.TRC(SCCGWA, BPRTDTL.RUN_FLG);
    }
    public void R000_TRANS_HOLIDAY_DATA() throws IOException,SQLException,Exception {
        BPRTDTL.KEY.TLR = BPCSTLSM.TLR;
        BPRTDTL.BEGIN_DT = BPCSTLSM.BEGIN_DT;
        BPRTDTL.BEGIN_TIME = BPCSTLSM.BEGIN_TIME;
        BPRTDTL.END_DT = BPCSTLSM.END_DT;
        BPRTDTL.END_TIME = BPCSTLSM.END_TIME;
        BPRTDTL.SIGN_FLG = BPCSTLSM.SIGN_FLG;
    }
    public void T000_CHECK_HOL_INFO() throws IOException,SQLException,Exception {
        if (BPCSTLSM.OPT == 'H') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.TRC(SCCGWA, "11111");
                CEP.TRC(SCCGWA, BPRTDTL.END_DT);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                CEP.TRC(SCCGWA, BPRTDTL.END_TIME);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
                if (BPRTDTL.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_IN_HOLIDAY;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (BPCSTLSM.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY;
                    S000_ERR_MSG_PROC();
                }
            }
        } else if (BPCSTLSM.OPT == 'M') {
            if (BPCSTLSM.BEGIN_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FURLOUGH_ERR;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCSTLSM.OPT == 'A') {
            CEP.TRC(SCCGWA, BPCSTLSM.BEGIN_DT);
            CEP.TRC(SCCGWA, BPRTDTL.BEGIN_DT);
            CEP.TRC(SCCGWA, BPCSTLSM.BEGIN_TIME);
            CEP.TRC(SCCGWA, BPRTDTL.BEGIN_TIME);
            if (BPCSTLSM.BEGIN_DT != BPRTDTL.BEGIN_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSM.END_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AHEAD_FURLOUGH_ERR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSM.END_DT > BPRTDTL.END_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_END_TIME_LARGER;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSM.BEGIN_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEGIN_DT_NOT_EFF);
            }
            if (BPRTDTL.BEGIN_DT <= SCCGWA.COMM_AREA.AC_DATE 
                && SCCGWA.COMM_AREA.AC_DATE <= BPRTDTL.END_DT) {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_H_STS);
            }
        } else if (BPCSTLSM.OPT == 'C') {
            if (BPCSTLSM.BEGIN_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_FURLOUGH_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLSM.END_DT < BPCSTLSM.BEGIN_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLTS() throws IOException,SQLException,Exception {
        BPCRTLTS.INFO.POINTER = BPRTDTL;
        BPCRTLTS.INFO.LEN = 93;
        IBS.CALLCPN(SCCGWA, CPN_R_TLTS_MAINTAIN, BPCRTLTS);
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        CEP.TRC(SCCGWA, BPCRTLTS.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
        if (((BPCSTLSM.OPT == 'Q' 
            && BPCSTLSM.SIGN_FLG == 'H') 
            || BPCSTLSM.OPT == 'H' 
            || BPCSTLSM.OPT == 'T') 
            && JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.TRC(SCCGWA, "ADD MOVE AT 2015");
        } else {
            if (BPCRTLTS.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S001_CALL_BPZRTLTS() throws IOException,SQLException,Exception {
        BPCRTLTS.INFO.POINTER = BPRTDTL;
        BPCRTLTS.INFO.LEN = 93;
        IBS.CALLCPN(SCCGWA, CPN_R_TLTS_MAINTAIN, BPCRTLTS);
        CEP.TRC(SCCGWA, BPCSTLSM.OPT);
        CEP.TRC(SCCGWA, BPCRTLTS.RC);
    }
    public void S000_CALL_BPZPQTLH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_QUERY_TLT_HOLIDAY, BPCPQTLH);
    }
    public void S000_CALL_BPZFPRRV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_PRIV_RVK, BPCFPRRV);
        if (BPCFPRRV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFPRRV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUTCVC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_BV_CHK, BPCUTCVC);
        if (BPCUTCVC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCUTCVC.RC);
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
