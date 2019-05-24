package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2134 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBRIS_RD;
    int JIBS_tmp_int;
    DBParm BPTTLVB_RD;
    String K_OUTPUT_FMT = "BP128";
    String CPN_S_OUT_STORE_F = "BP-S-OUT-STORE-F      ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_ADW_CLIB = "BP-R-ADW-CLIB";
    String K_HIS_REMARKS = "BR-SPCL-CS-MOV-OUT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INDEX = 0;
    int WS_CNT = 0;
    int WS_IN_BR1 = 0;
    int WS_IN_BR2 = 0;
    int WS_UP_BR = 0;
    char WS_OUT_ATTR = ' ';
    char WS_CHECK_ORG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSOSTF BPCSOSTF = new BPCSOSTF();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2134_AWA_2134 BPB2134_AWA_2134;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2134 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2134_AWA_2134>");
        BPB2134_AWA_2134 = (BPB2134_AWA_2134) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSOSTF);
        WS_CHECK_ORG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            B020_CHECK_ORG_FOR_CN();
            B050_GET_PLBOX_NO_FOR_CN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_CCY_FOR_CN();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B030_HISTORY_RECORD();
            B040_OUT_STORE_PROCESS_FOR_CN();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B010_CHECK_BR_RELA_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = "07";
        BPCPQORR.BR = BPB2134_AWA_2134.OUT_BR;
        CEP.TRC(SCCGWA, BPCPQORR.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORR();
        CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
        WS_IN_BR1 = BPCPQORR.REL_BR;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = "07";
            BPCPQORR.BR = BPB2134_AWA_2134.IN_BR;
            CEP.TRC(SCCGWA, BPB2134_AWA_2134.IN_BR);
            S000_CALL_BPZPQORR();
            WS_IN_BR2 = BPCPQORR.REL_BR;
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            if (WS_IN_BR2 == BPB2134_AWA_2134.OUT_BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR91);
            }
        } else {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = "07";
            BPCPQORR.BR = BPB2134_AWA_2134.IN_BR;
            CEP.TRC(SCCGWA, BPB2134_AWA_2134.IN_BR);
            S000_CALL_BPZPQORR();
            WS_IN_BR2 = BPCPQORR.REL_BR;
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0") 
                || WS_IN_BR2 != BPB2134_AWA_2134.OUT_BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.ORG_HAS_NO_ALLOT_RELAT);
            }
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P905";
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        B010_03_CHECK_CCY_INFO();
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-REL", BPCPQORR);
        if (BPCPQORR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORR.RC.RC_CODE);
        }
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2134_AWA_2134.CCY_INF[WS_INDEX-1].CCY)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REPEATED);
                    }
                }
            }
            if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ);
            }
            if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT - 1-1].AMT == 0 
                && BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].AMT != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ);
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].AMT == 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT);
                }
            }
            if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].AMT != 0 
                && BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT);
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_ORG_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB2134_AWA_2134.IN_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_SAME_NOTALLOWED);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        WS_UP_BR = BPCPQORG.SUPR_BR;
        WS_OUT_ATTR = BPCPQORG.ATTR;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, WS_UP_BR);
        if (WS_OUT_ATTR != '2' 
            && WS_OUT_ATTR != '3') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR77);
        } else {
            if (WS_OUT_ATTR == '2') {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPB2134_AWA_2134.IN_BR;
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                if (BPCPQORG.ATTR != '3') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR78);
                }
                if (BPCPQORG.SUPR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR79);
                }
            }
            if (WS_OUT_ATTR == '3') {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPB2134_AWA_2134.IN_BR;
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                if (BPCPQORG.ATTR != '2' 
                    && BPCPQORG.ATTR != '3') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR80);
                } else {
                    if (BPCPQORG.ATTR == '2') {
                        if (WS_UP_BR != BPB2134_AWA_2134.IN_BR) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR81);
                        }
                    }
                    if (BPCPQORG.ATTR == '3') {
                        if (BPCPQORG.SUPR_BR != WS_UP_BR) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR82);
                        }
                    }
                }
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2134_AWA_2134.IN_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (BPCPQORG.ORG_STS != 'O') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR73);
        }
    }
    public void B030_CHECK_CCY_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBF);
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
            BPRCLIB.KEY.CASH_TYP = "01";
        } else {
            BPRCLIB.KEY.CASH_TYP = " ";
        }
        BPRCLIB.KEY.CCY = BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY;
        BPCTLIBF.INFO.FUNC = 'Q';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY);
        }
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPB2134_AWA_2134.IN_BR;
        BPRBRIS.KEY.LMT_CCY = BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY;
        T000_CALL_BPTBRIS();
    }
    public void T000_CALL_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.where = "BR = :BPRBRIS.KEY.BR "
            + "AND LMT_CCY = :BPRBRIS.KEY.LMT_CCY";
        BPTBRIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRBRIS, this, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR127, BPRBRIS.KEY.LMT_CCY);
        } else {
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CLIB, BPCTLIBF);
    }
    public void B040_OUT_STORE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOSTF);
        BPCSOSTF.FMT = K_OUTPUT_FMT;
        BPCSOSTF.IN_BR = BPB2134_AWA_2134.IN_BR;
        BPCSOSTF.IN_TLR = BPB2134_AWA_2134.IN_TLR;
        BPCSOSTF.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCSOSTF.VB_BOX_OT = BPB2134_AWA_2134.OUT_FLG;
        BPCSOSTF.VB_BOX_IN = BPB2134_AWA_2134.IN_FLG;
        BPCSOSTF.ONWAY_DT = 0;
        BPCSOSTF.PLBOX_TP = BPRTLVB.PLBOX_TP;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].AMT;
            BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY;
            if (BPB2134_AWA_2134.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = "01";
            } else {
                BPCSOSTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = " ";
            }
        }
        S000_CALL_BPZSOSTF();
        BPB2134_AWA_2134.OUT_DT = BPCSOSTF.MOVE_DT;
        BPB2134_AWA_2134.CONF_NO = BPCSOSTF.CONF_NO;
        BPB2134_AWA_2134.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2134_AWA_2134.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B050_GET_PLBOX_NO_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.CRNT_TLR = BPB2134_AWA_2134.IN_TLR;
        BPRTLVB.KEY.BR = BPB2134_AWA_2134.IN_BR;
        S000_CALL_BPTTLVB();
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2134_AWA_2134.IN_TLR;
        S000_CALL_BPZFTLRQ();
        if (BPRTLVB.PLBOX_TP == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR70);
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR111);
        }
        if (BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR);
            }
            if (BPRTLVB.PLBOX_TP == '5') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
                }
            }
            if (BPB2134_AWA_2134.IN_FLG != '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR71);
            }
        }
        if (BPRTLVB.PLBOX_TP == '3' 
            || BPRTLVB.PLBOX_TP == '6') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
            }
            if (BPB2134_AWA_2134.IN_FLG != '0') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR72);
            }
        }
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPTTLVB();
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPRTLVB.PLBOX_TP == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR70);
        }
        if (BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR);
            }
            if (BPRTLVB.PLBOX_TP == '5') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
                }
            }
            if (BPB2134_AWA_2134.OUT_FLG != '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR71);
            }
        }
        if (BPRTLVB.PLBOX_TP == '3' 
            || BPRTLVB.PLBOX_TP == '6') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR);
            }
            if (BPB2134_AWA_2134.OUT_FLG != '0') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR72);
            }
        }
    }
    public void S000_CALL_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR";
        BPTTLVB_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.TLR_NO_CSBOX_OR_CLIB);
        }
    }
    public void S000_CALL_BPZSOSTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_OUT_STORE_F, BPCSOSTF);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
