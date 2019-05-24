package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6203 {
    String K_CPN_S_MAINTAIN_CNTY = "BP-S-MAINTAIN-CNTY  ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP741";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCNTY BPCSCNTY = new BPCSCNTY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6200_AWA_6200 BPB6200_AWA_6200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6203 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6200_AWA_6200>");
        BPB6200_AWA_6200 = (BPB6200_AWA_6200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSCNTY);
        BPCSCNTY.FUNC = 'D';
        R000_INPUT_DATA_PROCESS();
        S010_CALL_BPZSCNTY();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6200_AWA_6200.CNTY_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT;
            WS_FLD_NO = BPB6200_AWA_6200.CNTY_CD_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB6200_AWA_6200.EFF_DATE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT;
            WS_FLD_NO = BPB6200_AWA_6200.EFF_DATE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6200_AWA_6200.EFF_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            CEP.TRC(SCCGWA, SCCCKDT.RC);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6200_AWA_6200.EFF_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB6200_AWA_6200.EXP_DATE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DT_M_INPUT;
            WS_FLD_NO = BPB6200_AWA_6200.EXP_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB6200_AWA_6200.EXP_DATE < BPB6200_AWA_6200.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_GT_EXP_DT;
            WS_FLD_NO = BPB6200_AWA_6200.EXP_DATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCSCNTY.KEY.CODE = BPB6200_AWA_6200.CNTY_CD;
        BPCSCNTY.LOA_NAME = BPB6200_AWA_6200.LOA_NAME;
        BPCSCNTY.ENG_NAME = BPB6200_AWA_6200.ENG_NAME;
        BPCSCNTY.EFF_DATE = BPB6200_AWA_6200.EFF_DATE;
        BPCSCNTY.EXP_DATE = BPB6200_AWA_6200.EXP_DATE;
        BPCSCNTY.DATA.PDAYS = BPB6200_AWA_6200.PDAYS;
        BPCSCNTY.DATA.P_CUTTIME = BPB6200_AWA_6200.P_CUTTIM;
        BPCSCNTY.DATA.C_CUTTIME = BPB6200_AWA_6200.C_CUTTIM;
        BPCSCNTY.DATA.SWIFT_CODE = BPB6200_AWA_6200.SWIFT_CD;
        BPCSCNTY.DATA.IBAN = BPB6200_AWA_6200.IBAN;
        BPCSCNTY.DATA.CALR_CODE = BPB6200_AWA_6200.CALR_CD;
        BPCSCNTY.DATA.ISO_CODE = BPB6200_AWA_6200.ISO_CODE;
        BPCSCNTY.DATA.ISO_NUMER = BPB6200_AWA_6200.ISO_NUME;
        BPCSCNTY.DATA.CUR = BPB6200_AWA_6200.CURRENCY;
        BPCSCNTY.DATA.TIMEZONE = BPB6200_AWA_6200.TIMEZONE;
        BPCSCNTY.DATA.LANG_CODE = BPB6200_AWA_6200.LANG_CD;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSCNTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_MAINTAIN_CNTY, BPCSCNTY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
