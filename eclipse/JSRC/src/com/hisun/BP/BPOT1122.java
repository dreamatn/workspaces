package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1122 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP055";
    String CPN_FSTD_MAINTAIN = "BP-F-S-MAINTAIN-FSTD";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFSTD BPCOFSTD = new BPCOFSTD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPB1122_AWA_1122 BPB1122_AWA_1122;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1122 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1122_AWA_1122>");
        BPB1122_AWA_1122 = (BPB1122_AWA_1122) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCQCCY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_STD_RATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1122_AWA_1122.FEE_CD);
        CEP.TRC(SCCGWA, BPB1122_AWA_1122.REF_CCY);
        if (BPB1122_AWA_1122.FEE_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CD_MUST_INPUT;
            WS_FLD_NO = BPB1122_AWA_1122.FEE_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1122_AWA_1122.REF_CCY.trim().length() > 0) {
            BPCQCCY.DATA.CCY = BPB1122_AWA_1122.REF_CCY;
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                WS_FLD_NO = BPB1122_AWA_1122.REF_CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REF_CCY_MUST_INPUT;
            WS_FLD_NO = BPB1122_AWA_1122.REF_CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        R000_CHECK_RESULT_PROC();
    }
    public void B020_DELETE_STD_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSTD);
        BPCOFSTD.FUNC = 'D';
        BPCOFSTD.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSSTD();
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFSTD.FEE_CD = BPB1122_AWA_1122.FEE_CD;
        BPCOFSTD.KEY.REG_CODE = BPB1122_AWA_1122.ORG_CD;
        BPCOFSTD.KEY.CHN_NO = BPB1122_AWA_1122.CHNL_NO;
        BPCOFSTD.KEY.REF_CCY = BPB1122_AWA_1122.REF_CCY;
        BPCOFSTD.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFSTD.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSSTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FSTD_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFSTD;
        SCCCALL.ERR_FLDNO = BPB1122_AWA_1122.FEE_CD;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
