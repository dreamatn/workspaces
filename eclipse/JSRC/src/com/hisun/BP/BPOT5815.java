package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5815 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "SCZ01";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_CMZR_MAINTAIN = "BP-F-S-MAINTAIN-CMZR";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_NO = 0;
    short WS_CNT = 0;
    char WS_INPUT_ENDED = ' ';
    char WS_ENTI_FLG = ' ';
    char WS_CMZ_FLG = ' ';
    char WS_CMZ_FLG1 = ' ';
    char WS_CMZ_FLG2 = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCMZR BPCSCMZR = new BPCSCMZR();
    SCCGWA SCCGWA;
    BPB5810_AWA_5810 BPB5810_AWA_5810;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5815 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5810_AWA_5810>");
        BPB5810_AWA_5810 = (BPB5810_AWA_5810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_CMZ_PROT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCMZR);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.CMZ_FLG);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.CI_NO);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.CMZ_AC);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.EFF_DATE);
        if (BPB5810_AWA_5810.CMZ_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMZ_FLG_MUST_INP;
            WS_FLD_NO = BPB5810_AWA_5810.CMZ_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_CMZ_FLG = BPB5810_AWA_5810.CMZ_FLG;
        CEP.TRC(SCCGWA, WS_CMZ_FLG);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.EFF_DATE);
        if (BPB5810_AWA_5810.EFF_DATE == 0) {
            BPCSCMZR.EFF_DATE = 0;
        } else {
            BPCSCMZR.EFF_DATE = BPB5810_AWA_5810.EFF_DATE;
        }
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_FLG);
        CEP.TRC(SCCGWA, BPCSCMZR.CI_NO);
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_AC);
        CEP.TRC(SCCGWA, BPCSCMZR.EFF_DATE);
        R000_CHECK_RESULT_PROCESS();
    }
    public void B020_BROWSE_CMZ_PROT() throws IOException,SQLException,Exception {
        BPCSCMZR.FUNC = 'B';
        BPCSCMZR.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSCMZR();
    }
    public void R000_CHECK_RESULT_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSCMZR.CMZ_FLG = BPB5810_AWA_5810.CMZ_FLG;
        BPCSCMZR.CI_NO = BPB5810_AWA_5810.CI_NO;
        BPCSCMZR.CMZ_AC = BPB5810_AWA_5810.CMZ_AC;
        BPCSCMZR.CMZ_BIZ = BPB5810_AWA_5810.CMZ_BIZ;
        BPCSCMZR.EFF_DATE = BPB5810_AWA_5810.EFF_DATE;
    }
    public void S000_CALL_BPZSCMZR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CMZR_MAINTAIN, BPCSCMZR);
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
