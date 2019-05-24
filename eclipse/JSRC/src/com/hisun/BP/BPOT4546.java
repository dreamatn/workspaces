package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4546 {
    String JIBS_tmp_str[] = new String[10];
    char K_FUNC_BRW = 'B';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_BRW_ORG_REL = "BP-S-BRW-ORG-REL    ";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBORR BPCSBORR = new BPCSBORR();
    BPCSORRB BPCSORRB = new BPCSORRB();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB4540_AWA_4540 BPB4540_AWA_4540;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4546 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4540_AWA_4540>");
        BPB4540_AWA_4540 = (BPB4540_AWA_4540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_ORGREL_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4540_AWA_4540.BNK.equalsIgnoreCase("0") 
            || BPB4540_AWA_4540.BNK.charAt(0) == 0X00) {
            BPB4540_AWA_4540.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPB4540_AWA_4540.BR);
        if (BPB4540_AWA_4540.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4540_AWA_4540.BR;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_ORGREL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBORR);
        BPCSBORR.BNK = BPB4540_AWA_4540.BNK;
        if (BPB4540_AWA_4540.BR != 0 
            && BPB4540_AWA_4540.BR != 0X00) {
            BPCSBORR.BR = BPB4540_AWA_4540.BR;
        }
        if (BPB4540_AWA_4540.TYP.trim().length() > 0 
            && BPB4540_AWA_4540.TYP.charAt(0) != 0X00) {
            BPCSBORR.TYP = BPB4540_AWA_4540.TYP;
        }
        BPCSBORR.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSBORR();
    }
    public void S000_CALL_BPZSBORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_ORG_REL, BPCSBORR);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
