package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCSSP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTHIS_RD;
    String K_OUTPUT_FMT = "BP131";
    String CPN_F_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_P_VWA_WRITE = "BP-P-VWA-WRITE";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String WS_ERR_MSG = " ";
    int WS_CCY_CNT = 0;
    int WS_TEMP_CONF = 0;
    int WS_START_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_CNT = 0;
    int WS_CNT2 = 0;
    BPZSCSSP_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZSCSSP_WS_EWA_AC_NO();
    int WS_CNT3 = 0;
    char WS_HISTORY_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPCOCSSP BPCOCSSP = new BPCOCSSP();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    SCCGWA SCCGWA;
    BPCSCSSP BPCSCSSP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCSSP BPCSCSSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCSSP = BPCSCSSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSCSSP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOCSSP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B000 BEGIN");
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_TELLER_FOR_CN();
            B020_BOX_IN_OUT_PROC_FOR_CN();
            B050_WRT_THIS_PROC();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B030_AI_SUSP_PROC_FOR_CN();
                B040_OUTPUT_PROC();
            }
        } else {
            B010_CHECK_TELLER();
            B020_BOX_IN_OUT_PROC_PROC();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B030_AI_SUSP_PROC();
                B040_OUTPUT_PROC();
            }
        }
    }
    public void B010_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.PLBOX_TP = BPCSCSSP.BOX_FLG;
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL.substring(0, 3));
        CEP.TRC(SCCGWA, BPCSCSSP.TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        if (!SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
            BPRTLVB.CRNT_TLR = BPCSCSSP.TLR;
        } else {
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        BPCRTLVB.INFO.FUNC = 'R';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO == 'N') {
            if (BPCSCSSP.BOX_FLG == '5') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_LT_BOX);
            } else if (BPCSCSSP.BOX_FLG == '3') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_CASH_BOX);
            } else if (BPCSCSSP.BOX_FLG == '4') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ATM_NOT_HAVE_ATMBOX);
            } else if (BPCSCSSP.BOX_FLG == '2') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_Z_LIB);
            } else if (BPCSCSSP.BOX_FLG == '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_F_LIB);
            } else if (BPCSCSSP.BOX_FLG == '6') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_CASH_REG);
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
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
        if (BPCSCSSP.BOX_FLG == '1' 
            || BPCSCSSP.BOX_FLG == '2') {
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
            BPCPQBOX.DATA_INFO.CASH_TYP = BPCSCSSP.CASH_TYP;
            BPCPQBOX.DATA_INFO.CCY = BPCSCSSP.CCY;
            S000_CALL_BPZPQBOX();
            if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B015_AMT_AUTH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFAMTA);
        BPCFAMTA.FUNC = ' ';
        BPCFAMTA.AP_MMO = "BP";
        BPCFAMTA.TBL_NO = "" + 2300;
        JIBS_tmp_int = BPCFAMTA.TBL_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCFAMTA.TBL_NO = "0" + BPCFAMTA.TBL_NO;
        BPCFAMTA.CCY = BPCSCSSP.CCY;
        BPCFAMTA.AMT = BPCSCSSP.TOTAL_AMT;
        S000_CALL_BPZFAMTA();
    }
    public void B020_BOX_IN_OUT_PROC_FOR_CN() throws IOException,SQLException,Exception {
        if (BPCSCSSP.ML_OPT == '0') {
            IBS.init(SCCGWA, BPCUCSIN);
            if ((BPCSCSSP.BOX_FLG == '3' 
                    || BPCSCSSP.BOX_FLG == '5')) {
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.PLBOX_TYP = '3';
                BPCUCSIN.VB_FLG = '0';
            } else if (BPCSCSSP.BOX_FLG == '4') {
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.PLBOX_TYP = '4';
                BPCUCSIN.VB_TLR = BPCSCSSP.TLR;
                BPCUCSIN.VB_FLG = '0';
            } else if (BPCSCSSP.BOX_FLG == '6') {
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.PLBOX_TYP = '6';
                BPCUCSIN.VB_FLG = '0';
            } else {
                BPCUCSIN.CASH_STAT = '1';
                BPCUCSIN.VB_FLG = '1';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
            }
            CEP.TRC(SCCGWA, BPCUCSTO.VB_FLG);
            BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUCSIN.CASH_TYP = BPCSCSSP.CASH_TYP;
            BPCUCSIN.CCY = BPCSCSSP.CCY;
            BPCUCSIN.CS_KIND = BPCSCSSP.CS_KIND;
            BPCUCSIN.TX_AMT = BPCSCSSP.TOTAL_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCSSP.DATA_INFO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSIN.PAR_INFO);
            S000_CALL_BPZUCSIN();
            BPCOCSSP.PLBOX_NO = BPCUCSIN.PLBOX_NO;
        } else {
            CEP.TRC(SCCGWA, BPCSCSSP.BOX_FLG);
            CEP.TRC(SCCGWA, "SCSSP-BOX-FLG1");
            IBS.init(SCCGWA, BPCUCSTO);
            if ((BPCSCSSP.BOX_FLG == '3' 
                    || BPCSCSSP.BOX_FLG == '5')) {
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSTO.PLBOX_TYP = '3';
                BPCUCSTO.VB_FLG = '0';
            } else if (BPCSCSSP.BOX_FLG == '4') {
                BPCUCSTO.CASH_STAT = '2';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_TLR = BPCSCSSP.TLR;
                BPCUCSTO.PLBOX_TYP = '4';
                BPCUCSTO.VB_FLG = '0';
            } else if (BPCSCSSP.BOX_FLG == '6') {
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.PLBOX_TYP = '6';
                BPCUCSIN.VB_FLG = '0';
            } else {
                BPCUCSTO.CASH_STAT = '1';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSTO.VB_FLG = '1';
            }
            CEP.TRC(SCCGWA, BPCUCSTO.VB_FLG);
            BPCUCSTO.CASH_TYP = BPCSCSSP.CASH_TYP;
            BPCUCSTO.CCY = BPCSCSSP.CCY;
            BPCUCSTO.CS_KIND = BPCSCSSP.CS_KIND;
            BPCUCSTO.TX_AMT = BPCSCSSP.TOTAL_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCSSP.DATA_INFO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
            S000_CALL_BPZUCSTO();
            BPCOCSSP.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        }
    }
    public void B020_BOX_IN_OUT_PROC_PROC() throws IOException,SQLException,Exception {
        if (BPCSCSSP.ML_OPT == '0') {
            IBS.init(SCCGWA, BPCUCSIN);
            if (BPCSCSSP.BOX_FLG == '3' 
                || BPCSCSSP.BOX_FLG == '4') {
                CEP.TRC(SCCGWA, "SCSSP-MORE-CASH");
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.PLBOX_TYP = '3';
                BPCUCSIN.VB_FLG = '0';
            } else {
                CEP.TRC(SCCGWA, "SCSSP-LESS-CASH");
                BPCUCSIN.CASH_STAT = '1';
                BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSIN.VB_FLG = '1';
            }
            BPCUCSIN.CASH_TYP = BPCSCSSP.CASH_TYP;
            BPCUCSIN.CCY = BPCSCSSP.CCY;
            BPCUCSIN.CS_KIND = BPCSCSSP.CS_KIND;
            BPCUCSIN.TX_AMT = BPCSCSSP.TOTAL_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCSSP.DATA_INFO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSIN.PAR_INFO);
            S000_CALL_BPZUCSIN();
            BPCOCSSP.PLBOX_NO = BPCUCSIN.PLBOX_NO;
        } else {
            IBS.init(SCCGWA, BPCUCSTO);
            if (BPCSCSSP.BOX_FLG == '3' 
                || BPCSCSSP.BOX_FLG == '4') {
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSTO.PLBOX_TYP = '3';
                BPCUCSTO.VB_FLG = '0';
            } else {
                BPCUCSTO.CASH_STAT = '1';
                BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSTO.VB_FLG = '1';
            }
            BPCUCSTO.CASH_TYP = BPCSCSSP.CASH_TYP;
            BPCUCSTO.CCY = BPCSCSSP.CCY;
            BPCUCSTO.CS_KIND = BPCSCSSP.CS_KIND;
            BPCUCSTO.TX_AMT = BPCSCSSP.TOTAL_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCSSP.DATA_INFO);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
            S000_CALL_BPZUCSTO();
            BPCOCSSP.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        }
    }
    public void B030_AI_SUSP_PROC_FOR_CN() throws IOException,SQLException,Exception {
        if (BPCSCSSP.SUSP_TYPE == '1') {
            B033_SET_EWA_EVENTS_CASH_CN();
        } else if (BPCSCSSP.SUSP_TYPE == '3') {
            B034_SET_EWA_EVENTS_EXCH_CN();
        } else if (BPCSCSSP.SUSP_TYPE == '2') {
            B035_SET_EWA_EVENTS_ATM_CN();
        } else if (BPCSCSSP.SUSP_TYPE == '4') {
            B036_SET_EWA_EVENTS_CSGXZ_CN();
        }
    }
    public void B030_AI_SUSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPPRDQ);
        if (BPCSCSSP.BOX_FLG == '3' 
            || BPCSCSSP.BOX_FLG == '4') {
            BPCPPRDQ.DATA_INFO.STAT = '0';
        } else {
            BPCPPRDQ.DATA_INFO.STAT = '1';
        }
        BPCPPRDQ.DATA_INFO.CCY = BPCSCSSP.CCY;
        BPCPPRDQ.DATA_INFO.CS_KIND = BPCSCSSP.CS_KIND;
        B031_SET_EWA_BASIC_INF();
        B032_SET_EWA_EVENTS();
    }
    public void B031_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B032_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSSP.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CASHLESS";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CASHMORE";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSSP.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSSP.TOTAL_AMT;
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        if (BPCSCSSP.CCY == null) BPCSCSSP.CCY = "";
        JIBS_tmp_int = BPCSCSSP.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSCSSP.CCY += " ";
        BPCPOEWA.DATA.AC_NO = BPCPOEWA.DATA.AC_NO.substring(0, 9 - 1) + BPCSCSSP.CCY + BPCPOEWA.DATA.AC_NO.substring(9 + 3 - 1);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        S000_CALL_BPZPOEWA();
    }
    public void B033_SET_EWA_EVENTS_CASH_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSSP.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CSRLE";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSRMO";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.PAY_MAN = "CASH";
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSSP.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSSP.TOTAL_AMT;
        BPCPOEWA.DATA.DESC = BPCSCSSP.REMARK;
        S000_CALL_BPZPOEWA();
    }
    public void B034_SET_EWA_EVENTS_EXCH_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSSP.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CSLE";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSMO";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.PAY_MAN = "CASH";
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSSP.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSSP.TOTAL_AMT;
        BPCPOEWA.DATA.DESC = BPCSCSSP.REMARK;
        S000_CALL_BPZPOEWA();
    }
    public void B035_SET_EWA_EVENTS_ATM_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSSP.ML_OPT == '1') {
            BPCPOEWA.DATA.EVENT_CODE = "CSATMLE";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CSATMMO";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.PAY_MAN = "CASH";
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSSP.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSSP.TOTAL_AMT;
        BPCPOEWA.DATA.DESC = BPCSCSSP.REMARK;
        S000_CALL_BPZPOEWA();
    }
    public void B036_SET_EWA_EVENTS_CSGXZ_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCSCSSP.ML_OPT == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "CSGXZCR";
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_METHOD_ERR;
            S000_ERR_MSG_PROC();
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.PAY_MAN = "CASH";
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSSP.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSSP.TOTAL_AMT;
        BPCPOEWA.DATA.DESC = BPCSCSSP.REMARK;
        S000_CALL_BPZPOEWA();
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        BPCOCSSP.SUSP_TYPE = BPCSCSSP.SUSP_TYPE;
        BPCOCSSP.CS_KIND = BPCSCSSP.CS_KIND;
        BPCOCSSP.AMT = BPCSCSSP.TOTAL_AMT;
        BPCOCSSP.CASH_TYP = BPCSCSSP.CASH_TYP;
        BPCOCSSP.CCY = BPCSCSSP.CCY;
        BPCOCSSP.ML_OPT = BPCSCSSP.ML_OPT;
        BPCOCSSP.BOX_FLG = BPCSCSSP.BOX_FLG;
        BPCOCSSP.DT_CNT = BPCSCSSP.CNT;
        for (WS_START_CNT = 1; WS_START_CNT <= 12 
            && BPCSCSSP.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_VAL != 0; WS_START_CNT += 1) {
            BPCOCSSP.DATA_DT.DT_INFO[WS_START_CNT-1].P_PVAL = BPCSCSSP.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_VAL;
            BPCOCSSP.DATA_DT.DT_INFO[WS_START_CNT-1].P_MFLG = BPCSCSSP.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_MFLG;
            BPCOCSSP.DATA_DT.DT_INFO[WS_START_CNT-1].P_NUM = BPCSCSSP.DATA_INFO.PVAL_INFO[WS_START_CNT-1].CCY_NUM;
        }
        BPCOCSSP.REMARK = BPCSCSSP.REMARK;
        CEP.TRC(SCCGWA, BPCOCSSP.REMARK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCSSP;
        SCCFMT.DATA_LEN = 491;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_WRT_THIS_PROC() throws IOException,SQLException,Exception {
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
            if (BPCSCSSP.ML_OPT == '0') {
                BPRTHIS.DC_FLG = 'C';
                BPRTHIS.RCE_PBNO = BPRTLVB.KEY.PLBOX_NO;
            } else {
                BPRTHIS.DC_FLG = 'D';
                BPRTHIS.PAY_PBNO = BPRTLVB.KEY.PLBOX_NO;
            }
            BPRTHIS.CCY = BPCSCSSP.CCY;
            BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTHIS.AMT = BPCSCSSP.TOTAL_AMT;
            BPRTHIS.RMK = BPCSCSSP.REMARK;
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
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFAMTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-AMT-TBL-AUTH", BPCFAMTA);
        if (BPCFAMTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFAMTA.RC);
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
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CASH-PROD-INQ", BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
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
