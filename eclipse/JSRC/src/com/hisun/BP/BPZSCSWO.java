package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCSWO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTTHIS_RD;
    String K_OUTPUT_FMT = "BP133";
    String CPN_F_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_P_CASH_PROD_INQ = "BP-P-CASH-PROD-INQ";
    String CPN_P_VWA_WRITE = "BP-P-VWA-WRITE";
    String CPN_U_DEP_PROC = "DD-UNIT-DEP-PROC";
    String CPN_U_DRAW_PROC = "DD-UNIT-DRAW-PROC";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_Q_INFO_ACRI = "CI-INQ-ACR-INF";
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CNT2 = 0;
    int WS_START_CNT = 0;
    BPZSCSWO_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZSCSWO_WS_EWA_AC_NO();
    String WS_BR_AC = " ";
    String WS_AC = " ";
    String WS_SUS_ITM_R_C = " ";
    int WS_SUS_SEQ_R_C = 0;
    String WS_SUS_ITM_R_C1 = " ";
    int WS_CNT3 = 0;
    char WS_HISTORY_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRTHIS BPRTHIS = new BPRTHIS();
    CICQACRI CICQACRI = new CICQACRI();
    BPCOCSWO BPCOCSWO = new BPCOCSWO();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCSCSWO BPCSCSWO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCSWO BPCSCSWO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCSWO = BPCSCSWO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSCSWO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOCSWO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSCSWO.METHOD == '0') {
                B020_TRANS_AC_PROC_FOR_CN();
            } else if (BPCSCSWO.METHOD == '1') {
                B010_CHECK_TELLER_FOR_CN();
                B030_RETURN_SUP_PROC();
                B040_WRT_THIS_PROC();
            } else if (BPCSCSWO.METHOD == '2') {
                B050_GET_LOSS_PROC_FOR_CN();
            } else if (BPCSCSWO.METHOD == '3') {
                B070_TRANSFER_TO_QINGS_CTR();
            } else if (BPCSCSWO.METHOD == '4') {
                B070_TRANSFER_YINGJIE_AC();
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
            B080_AI_SUSP_PROC_FOR_CN();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B090_OUTPUT_PROC();
            }
        } else {
            B010_CHECK_TELLER();
            if (BPCSCSWO.METHOD == '0') {
                CEP.TRC(SCCGWA, "SCSWO-METHOD        VALUE 0.");
                B020_TRANS_AC_PROC();
            } else if (BPCSCSWO.METHOD == '1') {
                CEP.TRC(SCCGWA, "SCSWO-METHOD        VALUE 1.");
                B030_RETURN_SUP_PROC();
            } else if (BPCSCSWO.METHOD == '2') {
                CEP.TRC(SCCGWA, "SCSWO-METHOD        VALUE 2.");
                B050_GET_LOSS_PROC();
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
            B080_AI_SUSP_PROC();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B090_OUTPUT_PROC();
            }
        }
    }
    public void B010_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTLVB);
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.PLBOX_TP = BPCSCSWO.BOX_FLG;
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRTLVB.INFO.FUNC = 'R';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO == 'N') {
            if (BPCSCSWO.BOX_FLG == '5') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_LT_BOX);
            } else if (BPCSCSWO.BOX_FLG == '3') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_CASH_BOX);
            } else if (BPCSCSWO.BOX_FLG == '4') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ATM_NOT_HAVE_ATMBOX);
            } else if (BPCSCSWO.BOX_FLG == '2') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_Z_LIB);
            } else if (BPCSCSWO.BOX_FLG == '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_F_LIB);
            } else if (BPCSCSWO.BOX_FLG == '6') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_CASH_REG);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            }
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_TELLER() throws IOException,SQLException,Exception {
        if (BPCSCSWO.BOX_FLG == '1' 
            || BPCSCSWO.BOX_FLG == '2') {
            IBS.init(SCCGWA, BPCPQBOX);
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
            BPCRTLVB.INFO.FUNC = 'Q';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            WS_CNT = 0;
            while (BPCRTLVB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2') {
                    BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    WS_CNT = 1001;
                    if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                        S000_ERR_MSG_PROC();
                    }
                }
                BPCRTLVB.INFO.FUNC = 'N';
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                S000_CALL_BPZRTLVB();
            }
            BPCRTLVB.INFO.FUNC = 'E';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            if (BPCRTLVB.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
                S000_ERR_MSG_PROC();
            }
            BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPQBOX.DATA_INFO.CASH_TYP = BPCSCSWO.CASH_TYP;
            BPCPQBOX.DATA_INFO.CCY = BPCSCSWO.CCY;
            S000_CALL_BPZPQBOX();
            if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TRANS_AC_PROC_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCRAC.AC);
        if (BPCSCSWO.ML_OPT == '1') {
            CEP.TRC(SCCGWA, "XGQ1");
            IBS.init(SCCGWA, CICQACRI);
            IBS.init(SCCGWA, DDCUDRAC);
            IBS.init(SCCGWA, DDCUCRAC);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = BPCSCSWO.AC_NO;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                DDCUDRAC.CARD_NO = BPCSCSWO.AC_NO;
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                DDCUDRAC.AC = BPCSCSWO.AC_NO;
            }
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_ACNO;
                S000_ERR_MSG_PROC();
            }
            DDCUDRAC.CCY = BPCSCSWO.CCY;
            DDCUDRAC.CCY_TYPE = '1';
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.BANK_DR_FLG = 'Y';
            DDCUDRAC.TX_AMT = BPCSCSWO.TOTAL_AMT;
            DDCUCRAC.TX_MMO = "C020";
            DDCUDRAC.CHK_PSW_FLG = 'N';
            S000_CALL_DDZUDRAC();
        } else {
            CEP.TRC(SCCGWA, "XGQ2");
            IBS.init(SCCGWA, CICQACRI);
            IBS.init(SCCGWA, DDCUDRAC);
            IBS.init(SCCGWA, DDCUCRAC);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = BPCSCSWO.AC_NO;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                DDCUCRAC.CARD_NO = BPCSCSWO.AC_NO;
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                DDCUCRAC.AC = BPCSCSWO.AC_NO;
            }
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_ACNO;
                S000_ERR_MSG_PROC();
            }
            DDCUCRAC.CCY = BPCSCSWO.CCY;
            DDCUCRAC.CCY_TYPE = '1';
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.BANK_CR_FLG = 'Y';
            DDCUCRAC.TX_AMT = BPCSCSWO.TOTAL_AMT;
            DDCUCRAC.TX_MMO = "C020";
            S000_CALL_DDZUCRAC();
        }
    }
    public void B020_TRANS_AC_PROC() throws IOException,SQLException,Exception {
    }
    public void B030_RETURN_SUP_PROC() throws IOException,SQLException,Exception {
        if (BPCSCSWO.ML_OPT == '1') {
            IBS.init(SCCGWA, BPCUCSIN);
            if (BPCSCSWO.BOX_FLG == '3' 
                || BPCSCSWO.BOX_FLG == '4' 
                || BPCSCSWO.BOX_FLG == '5') {
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSIN.PLBOX_TYP = '3';
                BPCUCSIN.VB_FLG = '0';
            } else {
                BPCUCSIN.CASH_STAT = '1';
                BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.VB_FLG = '1';
            }
            BPCUCSIN.CASH_TYP = BPCSCSWO.CASH_TYP;
            BPCUCSIN.CCY = BPCSCSWO.CCY;
            BPCUCSIN.CS_KIND = BPCSCSWO.CS_KIND;
            BPCUCSIN.TX_AMT = BPCSCSWO.TOTAL_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCSWO.DATA_INFO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSIN.PAR_INFO);
            BPCUCSTO.REV_NO = BPCSCSWO.REV_NO;
            S000_CALL_BPZUCSIN();
        } else {
            IBS.init(SCCGWA, BPCUCSTO);
            if (BPCSCSWO.BOX_FLG == '3' 
                || BPCSCSWO.BOX_FLG == '4' 
                || BPCSCSWO.BOX_FLG == '5') {
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSTO.PLBOX_TYP = '3';
                BPCUCSTO.VB_FLG = '0';
            } else {
                BPCUCSTO.CASH_STAT = '1';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSTO.VB_FLG = '1';
            }
            BPCUCSTO.CASH_TYP = BPCSCSWO.CASH_TYP;
            BPCUCSTO.CCY = BPCSCSWO.CCY;
            BPCUCSTO.CS_KIND = BPCSCSWO.CS_KIND;
            BPCUCSTO.TX_AMT = BPCSCSWO.TOTAL_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCSWO.DATA_INFO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
            BPCUCSTO.REV_NO = BPCSCSWO.REV_NO;
            S000_CALL_BPZUCSTO();
        }
    }
    public void B050_GET_LOSS_PROC_FOR_CN() throws IOException,SQLException,Exception {
        B052_SET_EWA_EVENTS_FOR_CN();
    }
    public void B050_GET_LOSS_PROC() throws IOException,SQLException,Exception {
        B051_SET_EWA_BASIC_INF();
        B052_SET_EWA_EVENTS();
    }
    public void B051_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B052_SET_EWA_EVENTS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '0') {
            if (BPCSCSWO.SUSP_TYPE == '1') {
                BPCPOEWA.DATA.EVENT_CODE = "CSMOCB";
            } else if (BPCSCSWO.SUSP_TYPE == '2') {
                BPCPOEWA.DATA.EVENT_CODE = "CSMOCBO";
            } else if (BPCSCSWO.SUSP_TYPE == '3') {
                BPCPOEWA.DATA.EVENT_CODE = "CSMOCBO";
            }
        } else {
            if (BPCSCSWO.SUSP_TYPE == '1') {
                BPCPOEWA.DATA.EVENT_CODE = "CSLECB";
            } else if (BPCSCSWO.SUSP_TYPE == '2') {
                BPCPOEWA.DATA.EVENT_CODE = "CSLECBO";
            } else if (BPCSCSWO.SUSP_TYPE == '3') {
                BPCPOEWA.DATA.EVENT_CODE = "CSLECBO";
            }
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        if (BPCSCSWO.CCY == null) BPCSCSWO.CCY = "";
        JIBS_tmp_int = BPCSCSWO.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSCSWO.CCY += " ";
        BPCPOEWA.DATA.AC_NO = BPCPOEWA.DATA.AC_NO.substring(0, 9 - 1) + BPCSCSWO.CCY + BPCPOEWA.DATA.AC_NO.substring(9 + 3 - 1);
        S000_CALL_BPZPOEWA();
    }
    public void B052_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "CSMORECB";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSLESSCB";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        S000_CALL_BPZPOEWA();
    }
    public void B060_TRANSFER_TO_CREDIT_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "ISTL";
        if (BPCSCSWO.ML_OPT == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "XYKHXCR";
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_METHOD_ERR;
            S000_ERR_MSG_PROC();
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        S000_CALL_BPZPOEWA();
    }
    public void B070_TRANSFER_TO_QINGS_CTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "3";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = "BK001";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = "QSSH";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        WS_SUS_ITM_R_C = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
        WS_SUS_SEQ_R_C = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
        if (WS_SUS_ITM_R_C == null) WS_SUS_ITM_R_C = "";
        JIBS_tmp_int = WS_SUS_ITM_R_C.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_SUS_ITM_R_C += " ";
        WS_SUS_ITM_R_C1 = WS_SUS_ITM_R_C.substring(0, 8);
        CEP.TRC(SCCGWA, WS_SUS_ITM_R_C);
        CEP.TRC(SCCGWA, WS_SUS_ITM_R_C1);
        CEP.TRC(SCCGWA, WS_SUS_SEQ_R_C);
        WS_BR_AC = "706660500";
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_BR_AC;
        CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
        BPCSCSWO.AC_NO = " ";
        BPCSCSWO.AC_NO = AICUUPIA.DATA.AC_NO;
        CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        AICUUPIA.DATA.AMT = BPCSCSWO.TOTAL_AMT;
        AICUUPIA.DATA.CCY = BPCSCSWO.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.PAY_MAN = "CASH";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void B070_TRANSFER_YINGJIE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        WS_AC = " ";
        CEP.TRC(SCCGWA, BPCSCSWO.BR);
        BPCPQORG.BR = BPCSCSWO.BR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (BPCPQORG.ATTR == '0' 
            || BPCPQORG.ATTR == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR185);
        }
        if (BPCPQORG.ATTR == '2') {
            WS_AC = BPCSCSWO.BR;
            CEP.TRC(SCCGWA, WS_AC);
        }
        if (BPCPQORG.ATTR == '3') {
            WS_AC = BPCPQORG.SUPR_BR;
            CEP.TRC(SCCGWA, WS_AC);
        }
        CEP.TRC(SCCGWA, WS_AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_AC;
        CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
        BPCSCSWO.AC_NO = " ";
        BPCSCSWO.AC_NO = AICUUPIA.DATA.AC_NO;
        CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        AICUUPIA.DATA.AMT = BPCSCSWO.TOTAL_AMT;
        AICUUPIA.DATA.CCY = BPCSCSWO.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.PAY_MAN = "CASH";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
    }
    public void B040_WRT_THIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.VCH_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO);
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTHISF);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            S000_GET_SEQ();
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            WS_CNT3 += 1;
            BPRTHIS.KEY.SEQ = WS_CNT3;
            BPRTHIS.AP_CODE = SCCGWA.COMM_AREA.AP_MMO;
            BPRTHIS.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (BPCSCSWO.ML_OPT == '0') {
                BPRTHIS.DC_FLG = 'D';
                BPRTHIS.PAY_PBNO = BPRTLVB.KEY.PLBOX_NO;
            } else {
                BPRTHIS.DC_FLG = 'C';
                BPRTHIS.RCE_PBNO = BPRTLVB.KEY.PLBOX_NO;
            }
            BPRTHIS.CCY = BPCSCSWO.CCY;
            BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTHIS.AMT = BPCSCSWO.TOTAL_AMT;
            BPRTHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRTHIS.STS = '0';
            BPCTHISF.INFO.FUNC = 'A';
            BPCTHISF.POINTER = BPRTHIS;
            BPCTHISF.LEN = 959;
            S000_CALL_BPZTHISF();
        } else {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
            S000_GET_SEQ();
            IBS.init(SCCGWA, BPRTHIS);
            for (WS_CNT2 = 1; WS_CNT2 <= WS_CNT3; WS_CNT2 += 1) {
                BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRTHIS.KEY.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
                BPRTHIS.KEY.SEQ = WS_CNT2;
                BPCTHISF.INFO.FUNC = 'R';
                BPCTHISF.POINTER = BPRTHIS;
                BPCTHISF.LEN = 959;
                S000_CALL_BPZTHISF();
                if (BPRTHIS.STS != '0') {
                    CEP.TRC(SCCGWA, "NOT THIS-NORMA");
                } else {
                    BPRTHIS.STS = '1';
                    BPCTHISF.INFO.FUNC = 'U';
                    BPCTHISF.POINTER = BPRTHIS;
                    BPCTHISF.LEN = 959;
                    S000_CALL_BPZTHISF();
                    CEP.TRC(SCCGWA, "FIND ERR");
                }
            }
        }
    }
    public void B080_AI_SUSP_PROC_FOR_CN() throws IOException,SQLException,Exception {
        if (BPCSCSWO.SUSP_TYPE == '1') {
            B083_SET_EWA_EVENTS_CASH_CN();
        } else if (BPCSCSWO.SUSP_TYPE == '3') {
            B084_SET_EWA_EVENTS_EXCH_CN();
        } else if (BPCSCSWO.SUSP_TYPE == '2') {
            B085_SET_EWA_EVENTS_ATM_CN();
        } else if (BPCSCSWO.SUSP_TYPE == '4') {
            B086_SET_CSGXZ_EVENTS_ATM_CN();
        }
    }
    public void B080_AI_SUSP_PROC() throws IOException,SQLException,Exception {
        B081_SET_EWA_BASIC_INF();
        B082_SET_EWA_EVENTS();
    }
    public void B081_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B082_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "CSMORECA";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSLESSCA";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        if (BPCPPRDQ.DATA_INFO.CCY == null) BPCPPRDQ.DATA_INFO.CCY = "";
        JIBS_tmp_int = BPCPPRDQ.DATA_INFO.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCPPRDQ.DATA_INFO.CCY += " ";
        BPCPOEWA.DATA.AC_NO = BPCPOEWA.DATA.AC_NO.substring(0, 9 - 1) + BPCPPRDQ.DATA_INFO.CCY + BPCPOEWA.DATA.AC_NO.substring(9 + 3 - 1);
        BPCPOEWA.DATA.RVS_NO = BPCSCSWO.REV_NO;
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_STM_IND);
        S000_CALL_BPZPOEWA();
    }
    public void B083_SET_EWA_EVENTS_CASH_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CSRLECA";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSRMOCA";
        }
        BPCPOEWA.DATA.BR_OLD = BPCSCSWO.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSCSWO.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        BPCPOEWA.DATA.RVS_NO = BPCSCSWO.REV_NO;
        BPCPOEWA.DATA.THEIR_AC = BPCSCSWO.AC_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
    }
    public void B084_SET_EWA_EVENTS_EXCH_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CSLECA";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSMOCA";
        }
        BPCPOEWA.DATA.BR_OLD = BPCSCSWO.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSCSWO.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        BPCPOEWA.DATA.RVS_NO = BPCSCSWO.REV_NO;
        BPCPOEWA.DATA.THEIR_AC = BPCSCSWO.AC_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
    }
    public void B085_SET_EWA_EVENTS_ATM_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CSATMLEC";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSATMMOC";
        }
        BPCPOEWA.DATA.BR_OLD = BPCSCSWO.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSCSWO.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        BPCPOEWA.DATA.RVS_NO = BPCSCSWO.REV_NO;
        BPCPOEWA.DATA.THEIR_AC = BPCSCSWO.AC_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
    }
    public void B086_SET_CSGXZ_EVENTS_ATM_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSWO.ML_OPT == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "CSGXZDR";
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_METHOD_ERR;
            S000_ERR_MSG_PROC();
        }
        BPCPOEWA.DATA.BR_OLD = BPCSCSWO.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSCSWO.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSWO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSWO.TOTAL_AMT;
        BPCPOEWA.DATA.RVS_NO = BPCSCSWO.REV_NO;
        BPCPOEWA.DATA.THEIR_AC = BPCSCSWO.AC_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
    }
    public void B090_OUTPUT_PROC() throws IOException,SQLException,Exception {
        BPCOCSWO.CS_KIND = BPCSCSWO.CS_KIND;
        BPCOCSWO.AMT = BPCSCSWO.TOTAL_AMT;
        BPCOCSWO.CASH_TYP = BPCSCSWO.CASH_TYP;
        BPCOCSWO.CCY = BPCSCSWO.CCY;
        BPCOCSWO.ML_OPT = BPCSCSWO.ML_OPT;
        BPCOCSWO.BOX_FLG = BPCSCSWO.BOX_FLG;
        BPCOCSWO.METHOD = BPCSCSWO.METHOD;
        BPCOCSWO.DEBIT_AC = BPCSCSWO.AC_NO;
        BPCOCSWO.BR = BPCSCSWO.BR;
        BPCOCSWO.CREV_NO = BPCSCSWO.REV_NO;
        BPCOCSWO.DT_CNT = BPCSCSWO.DT_CNT;
        for (WS_START_CNT = 1; WS_START_CNT <= 12 
            && BPCSCSWO.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_VAL != 0; WS_START_CNT += 1) {
            BPCOCSWO.DATA_DT.DT_INFO[WS_START_CNT-1].P_PVAL = BPCSCSWO.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_VAL;
            BPCOCSWO.DATA_DT.DT_INFO[WS_START_CNT-1].P_MFLG = BPCSCSWO.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_MFLG;
            BPCOCSWO.DATA_DT.DT_INFO[WS_START_CNT-1].P_NUM = BPCSCSWO.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_NUM;
        }
        BPCOCSWO.AC_TYPE = BPCSCSWO.AC_TYPE;
        BPCOCSWO.SUSP_TYPE = BPCSCSWO.SUSP_TYPE;
        CEP.TRC(SCCGWA, BPCOCSWO.DATA_DT);
        CEP.TRC(SCCGWA, BPCSCSWO.DATA_INFO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_STM_IND);
        CEP.TRC(SCCGWA, BPCSCSWO.AC_NO);
        CEP.TRC(SCCGWA, BPCOCSWO.DEBIT_AC);
        CEP.TRC(SCCGWA, BPCOCSWO);
        CEP.TRC(SCCGWA, BPCOCSWO.CS_KIND);
        CEP.TRC(SCCGWA, BPCOCSWO.AMT);
        CEP.TRC(SCCGWA, BPCOCSWO.CASH_TYP);
        CEP.TRC(SCCGWA, BPCOCSWO.CCY);
        CEP.TRC(SCCGWA, BPCOCSWO.ML_OPT);
        CEP.TRC(SCCGWA, BPCOCSWO.BOX_FLG);
        CEP.TRC(SCCGWA, BPCOCSWO.METHOD);
        CEP.TRC(SCCGWA, BPCOCSWO.DEBIT_AC);
        CEP.TRC(SCCGWA, BPCOCSWO.BR);
        CEP.TRC(SCCGWA, BPCOCSWO.CREV_NO);
        CEP.TRC(SCCGWA, BPCOCSWO.DT_CNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCSWO;
        SCCFMT.DATA_LEN = 404;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_Q_INFO_ACRI, CICQACRI);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DEP_PROC, DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DRAW_PROC, DDCUDRAC);
    }
    public void S000_CALL_BPZUCSIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_IN, BPCUCSIN);
        if (BPCUCSIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSIN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CASH_PROD_INQ, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            CEP.TRC(SCCGWA, BPCPPRDQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTHISF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.SEQ);
        IBS.CALLCPN(SCCGWA, CPN_R_HISF, BPCTHISF);
        CEP.TRC(SCCGWA, BPCTHISF.RC.RC_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCTHISF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHISF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_GET_SEQ() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.set = "WS-CNT3=COUNT(*)";
        BPTTHIS_RD.where = "'DATE' = :BPRTHIS.KEY.DATE "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        IBS.GROUP(SCCGWA, BPRTHIS, this, BPTTHIS_RD);
        CEP.TRC(SCCGWA, WS_CNT3);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
