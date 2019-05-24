package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2129 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTALIB_RD;
    DBParm BPTAPAR_RD;
    String CPN_S_OUT_STORE = "BP-S-OUT-STORE      ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String K_HIS_REMARKS = "BR-CS-MOV-OUT-RES";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_I = 0;
    double WS_GD_AMT = 0;
    int WS_CNT1 = 0;
    int WS_CNT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPOT2129_WS_CONF_INFO WS_CONF_INFO = new BPOT2129_WS_CONF_INFO();
    int WS_CONF_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSOSTO BPCSOSTO = new BPCSOSTO();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPAR BPRAPAR = new BPRAPAR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2120_AWA_2120 BPB2120_AWA_2120;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT-1]);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT-1]);
        for (WS_CNT = 1; WS_CNT <= 2 
            && JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0"); WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT-1]);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT1-1]);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 200 
            && JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0"); WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CONF_NO);
            WS_CONF_NO = WS_CONF_INFO.WS_CONF_G[WS_CNT1-1].WS_CONF_N;
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT1-1]);
            CEP.TRC(SCCGWA, WS_CONF_NO);
            if (WS_CNT > 2) {
                A000_REDF_AWA();
            }
            B000_MAIN_PROC();
            IBS.CPY2CLS(SCCGWA, BPB2120_AWA_2120.CONF_NOG, WS_CONF_INFO);
        }
        CEP.TRC(SCCGWA, "BPOT2129 return!");
        Z_RET();
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPRAPAR);
        BPRALIB.CONF_NO = WS_CONF_NO;
        CEP.TRC(SCCGWA, BPRALIB.CONF_NO);
        T000_READ_BPTALIB();
        CEP.TRC(SCCGWA, BPRALIB.KEY.APP_NO);
        BPRAPAR.KEY.APP_NO = BPRALIB.KEY.APP_NO;
        CEP.TRC(SCCGWA, BPRAPAR.KEY.APP_NO);
        CEP.TRC(SCCGWA, WS_CNT);
        T000_READ_BPTAPAR();
        BPB2120_AWA_2120.CCY_INFO[1-1].CASH_TYP = "01";
        BPB2120_AWA_2120.CCY_INFO[1-1].CCY = BPRAPAR.APP_CCY;
        BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT = BPRAPAR.OUT_AMT;
        BPB2120_AWA_2120.CS_KIND = '0';
        CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.IN_BR);
        if (BPRALIB.APP_TYPE == '0') {
            BPB2120_AWA_2120.IN_BR = BPRALIB.APP_BR;
        } else {
            BPB2120_AWA_2120.IN_BR = BPRALIB.UP_BR;
        }
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.IN_BR);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CS_KIND);
    }
    public void T000_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "CONF_NO = :BPRALIB.CONF_NO";
        BPTALIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
    }
    public void T000_READ_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        BPTAPAR_RD.where = "APP_NO = :BPRAPAR.KEY.APP_NO";
        BPTAPAR_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPAR, this, BPTAPAR_RD);
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2120_AWA_2120>");
        BPB2120_AWA_2120 = (BPB2120_AWA_2120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSOSTO);
        IBS.CPY2CLS(SCCGWA, BPB2120_AWA_2120.CONF_NOG, WS_CONF_INFO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B050_GET_PLBOX_NO_FOR_CN();
            B020_CHECK_ORG();
            CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[1-1].CCY);
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B030_HISTORY_RECORD();
            B040_OUT_STORE_PROCESS();
        } else {
            B010_CHECK_INPUT();
            B020_CHECK_ORG();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B040_OUT_STORE_PROCESS();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CS_KIND = BPB2120_AWA_2120.CS_KIND;
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CS_KIND);
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
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.TR_TLR);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CS_KIND);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.TR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, WS_CONF_NO);
        if (!BPB2120_AWA_2120.TR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2120_AWA_2120.TR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
            && BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            WS_I = WS_INFO_CNT - WS_START_CNT + 1;
            BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2120_AWA_2120.P_INFO[WS_INFO_CNT-1].P_MFLG;
        }
        BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2120_AWA_2120.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
    }
    public void B040_OUT_STORE_PROCESS() throws IOException,SQLException,Exception {
        BPCSOSTO.CS_KIND = BPB2120_AWA_2120.CS_KIND;
        BPCSOSTO.IN_BR = BPB2120_AWA_2120.IN_BR;
        BPCSOSTO.MOVE_DT = BPB2120_AWA_2120.MOVE_DT;
        BPCSOSTO.CONF_NO = WS_CONF_NO;
        BPCSOSTO.CNT = WS_CNT;
        CEP.TRC(SCCGWA, BPCSOSTO.CNT);
        S000_CALL_BPZSOSTO();
    }
    public void B050_GET_PLBOX_NO_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '1';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            BPRTLVB.PLBOX_TP = '2';
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                BPRTLVB.PLBOX_TP = '5';
                BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        BPCSOSTO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
    }
    public void S000_CALL_BPZSOSTO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_OUT_STORE;
        SCCCALL.COMMAREA_PTR = BPCSOSTO;
        SCCCALL.ERR_FLDNO = BPB2120_AWA_2120.IN_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
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
