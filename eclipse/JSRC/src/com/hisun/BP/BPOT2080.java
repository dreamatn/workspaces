package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2080 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "SCZ01";
    String K_BRANCH = "11";
    String CPN_S_BRW_LIBT = "BP-S-BRW-LIBT       ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSLIBT BPCSLIBT = new BPCSLIBT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2080_AWA_2080 BPB2080_AWA_2080;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2080 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2080_AWA_2080>");
        BPB2080_AWA_2080 = (BPB2080_AWA_2080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2080_AWA_2080.BR);
        CEP.TRC(SCCGWA, BPB2080_AWA_2080.PLBOX_TP);
        CEP.TRC(SCCGWA, BPB2080_AWA_2080.CCY);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_TLR_INFO();
            B020_BROWSE_CLIB_TOT_FOR_CN();
        } else {
            B010_CHECK_TLR_INFO();
            B020_BROWSE_CLIB_TOT_PROCESS();
        }
    }
    public void B010_CHECK_TLR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_DIREC);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPB2080_AWA_2080.BR);
        BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFTLCM.BR = BPB2080_AWA_2080.BR;
        S000_CALL_BPZFTLCM();
        if (BPCFTLCM.AUTH_FLG != 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NO_AUTH_TO_BR);
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B020_BROWSE_CLIB_TOT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2080_AWA_2080.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB2080_AWA_2080.BR_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQORG.TYP);
        IBS.init(SCCGWA, BPCSLIBT);
        if (BPCPQORG.TYP.equalsIgnoreCase(K_BRANCH)) {
            BPCSLIBT.FUNC = 'A';
        } else {
            BPCSLIBT.FUNC = 'B';
        }
        BPCSLIBT.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSLIBT.BR = BPB2080_AWA_2080.BR;
        BPCSLIBT.PLBOX_TP = BPB2080_AWA_2080.PLBOX_TP;
        BPCSLIBT.CCY = BPB2080_AWA_2080.CCY;
        S000_CALL_BPZSLIBT();
    }
    public void B020_BROWSE_CLIB_TOT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2080_AWA_2080.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB2080_AWA_2080.BR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCSLIBT);
        BPCSLIBT.FUNC = 'B';
        BPCSLIBT.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSLIBT.BR = BPB2080_AWA_2080.BR;
        BPCSLIBT.PLBOX_TP = BPB2080_AWA_2080.PLBOX_TP;
        BPCSLIBT.CCY = BPB2080_AWA_2080.CCY;
        BPCSLIBT.INQ_TYP = BPB2080_AWA_2080.INQ_TYP;
        S000_CALL_BPZSLIBT();
    }
    public void S000_CALL_BPZSLIBT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_LIBT, BPCSLIBT);
        if (BPCSLIBT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSLIBT.RC);
            S000_ERR_MSG_PROC();
        }
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
