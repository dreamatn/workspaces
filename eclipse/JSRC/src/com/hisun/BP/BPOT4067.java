package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4067 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    BPOT4067_WS_NM WS_NM = new BPOT4067_WS_NM();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    AICPQITM AICQITM = new AICPQITM();
    SCCGWA SCCGWA;
    BPB4067_AWA_4067 BPB4067_AWA_4067;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4067 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4067_AWA_4067>");
        BPB4067_AWA_4067 = (BPB4067_AWA_4067) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MST_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MST_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        S000_DATA_OUTPUT();
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.COA_FLG = BPB4067_AWA_4067.COA_FLG;
        AICQITM.INPUT_DATA.NO = BPB4067_AWA_4067.NO;
        WS_FND_FLG = 'Y';
        S000_CALL_AIZPQITM();
        if (WS_FND_FLG == 'N') {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_ITMNO_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_NM.WS_ENG_NM = AICQITM.OUTPUT_DATA.ENG_NM;
        WS_NM.WS_CHS_NM = AICQITM.OUTPUT_DATA.CHS_NM;
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP218";
        SCCFMT.DATA_PTR = WS_NM;
        SCCFMT.DATA_LEN = 240;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        if (AICQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND)) {
                WS_FND_FLG = 'N';
            } else {
                WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
