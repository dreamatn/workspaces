package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3310 {
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT3310_WS_DATA WS_DATA = new BPOT3310_WS_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSQTXH BPCSQTXH = new BPCSQTXH();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPORUP BPCPORUP = new BPCPORUP();
    SCCGWA SCCGWA;
    BPB3300_AWA_3300 BPB3300_AWA_3300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3310 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3300_AWA_3300>");
        BPB3300_AWA_3300 = (BPB3300_AWA_3300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3300_AWA_3300.TL_ID.trim().length() > 0) {
            BPCFTLRQ.INFO.TLR = BPB3300_AWA_3300.TL_ID;
            S000_CALL_BPZFTLRQ();
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
            WS_DATA.WS_TLR_BR1 = BPCFTLRQ.INFO.TLR_BR;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZFTLRQ();
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
            WS_DATA.WS_TLR_BR2 = BPCFTLRQ.INFO.TLR_BR;
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = WS_DATA.WS_TLR_BR1;
            S000_CALL_BPZPORUP();
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR);
            CEP.TRC(SCCGWA, WS_DATA.WS_TLR_BR2);
            if (WS_DATA.WS_TLR_BR2 == BPCPORUP.DATA_INFO.BR 
                || WS_DATA.WS_TLR_BR2 == BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR 
                || WS_DATA.WS_TLR_BR2 == BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR 
                || WS_DATA.WS_TLR_BR2 == BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR) {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_INQUERY);
            }
        }
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.AC_DT);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.TL_ID);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.AP_CODE);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.TR_CODE);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.JNO);
        CEP.TRC(SCCGWA, BPB3300_AWA_3300.TR_CNT);
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSQTXH);
        CEP.TRC(SCCGWA, "CCC");
        BPCSQTXH.DATA.AC_DT = BPB3300_AWA_3300.AC_DT;
        CEP.TRC(SCCGWA, "BBB");
        BPCSQTXH.DATA.TL_ID = BPB3300_AWA_3300.TL_ID;
        CEP.TRC(SCCGWA, "DDD");
        BPCSQTXH.DATA.AP_CODE = BPB3300_AWA_3300.AP_CODE;
        CEP.TRC(SCCGWA, "EEE");
        BPCSQTXH.DATA.TR_CODE = BPB3300_AWA_3300.TR_CODE;
        CEP.TRC(SCCGWA, "FFF");
        BPCSQTXH.DATA.JNO = BPB3300_AWA_3300.JNO;
        CEP.TRC(SCCGWA, "GGG");
        BPCSQTXH.DATA.TR_CNT = BPB3300_AWA_3300.TR_CNT;
        CEP.TRC(SCCGWA, "KKK");
        BPCSQTXH.DATA.TR_AMT = BPB3300_AWA_3300.TR_AMT;
        CEP.TRC(SCCGWA, "AAAA");
        BPCSQTXH.FUNC = 'B';
        S000_CALL_BPZSQTXH();
    }
    public void S000_CALL_BPZSQTXH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-INQ-TX-HIS", BPCSQTXH);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
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
