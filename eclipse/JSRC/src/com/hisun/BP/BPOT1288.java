package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1288 {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String K_OUTPUT_FMT = "BP078";
    String CPN_S_P_GET_FEE = "BP-S-P-GET-FEE";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_ITM = 0;
    double WS_FEE_AMT = 0;
    short WS_CNT = 0;
    String WS_REG_CODE = " ";
    String WS_TS = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPCSMUTF BPCSMUTF = new BPCSMUTF();
    BPCOCHGF BPCOCHGF = new BPCOCHGF();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPRFBAS BPRFBAS = new BPRFBAS();
    CIRACR CIRACR = new CIRACR();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    SCCGWA SCCGWA;
    BPB1288_AWA_1288 BPB1288_AWA_1288;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1288 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1288_AWA_1288>");
        BPB1288_AWA_1288 = (BPB1288_AWA_1288) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_CHECK_INPUT_CN();
            B020_MULT_FEE_S_CN();
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB1288_AWA_1288.FEE_INFO[1-1].FEE_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT;
            S000_ERR_MSG_PROC();
        }
        for (WS_CNT = 1; WS_CNT <= BPB1288_AWA_1288.FEE_CNT; WS_CNT += 1) {
            if (BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].AMO_FLG == 'Y' 
                && (BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].STRDT == 0 
                || BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].ENDDT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMODT_MUSTINPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_MULT_FEE_S_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMUTF);
        BPCSMUTF.CMMT_NO = BPB1288_AWA_1288.CMMT_NO;
        BPCSMUTF.PROC_DT = BPB1288_AWA_1288.PROC_DT;
        BPCSMUTF.CLT_TYPE = BPB1288_AWA_1288.CLT_TYPE;
        BPCSMUTF.DRMCR_FLG = BPB1288_AWA_1288.DRMCR;
        BPCSMUTF.CHG_CI_NO = BPB1288_AWA_1288.CI_NO;
        BPCSMUTF.CHG_AC_TY = BPB1288_AWA_1288.AC_TY;
        BPCSMUTF.CHG_AC = BPB1288_AWA_1288.CHG_AC;
        BPCSMUTF.CARD_PSBK_NO = BPB1288_AWA_1288.CARD_NO;
        BPCSMUTF.CHG_CCY = BPB1288_AWA_1288.CHG_CCY;
        BPCSMUTF.CHG_CCY_TYPE = BPB1288_AWA_1288.CCY_TYPE;
        BPCSMUTF.CHG_MOD = BPB1288_AWA_1288.CHG_MOD;
        BPCSMUTF.BAT_ORG_DT = BPB1288_AWA_1288.ORG_DT;
        BPCSMUTF.BAT_ORG_JRN = BPB1288_AWA_1288.ORG_JRN;
        BPCSMUTF.BAT_ORG_SEQ = BPB1288_AWA_1288.ORG_SEQ;
        BPCSMUTF.CHNL_NO = BPB1288_AWA_1288.CHNL;
        BPCSMUTF.BSNS_NO = BPB1288_AWA_1288.BSNS_NO;
        BPCSMUTF.HLD_NO = BPB1288_AWA_1288.HLD_NO;
        BPCSMUTF.REMARK = BPB1288_AWA_1288.REMARK;
        BPCSMUTF.FEE_CNT = BPB1288_AWA_1288.FEE_CNT;
        for (WS_CNT = 1; WS_CNT <= BPB1288_AWA_1288.FEE_CNT; WS_CNT += 1) {
            BPCSMUTF.FEE_INFO[WS_CNT-1].TRT_BR = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].TRT_BR;
            BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_BR = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].CHG_BR;
            BPCSMUTF.FEE_INFO[WS_CNT-1].FEE_CODE = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].FEE_CODE;
            BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_AMT = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].CHG_AMT;
            BPCSMUTF.FEE_INFO[WS_CNT-1].AMO_FLG = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].AMO_FLG;
            BPCSMUTF.FEE_INFO[WS_CNT-1].AMO_STRDT = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].STRDT;
            BPCSMUTF.FEE_INFO[WS_CNT-1].AMO_ENDDT = BPB1288_AWA_1288.FEE_INFO[WS_CNT-1].ENDDT;
        }
        BPCSMUTF.VAT_AMT = BPB1288_AWA_1288.VAT_AMT;
        S000_CALL_BPZSMUTF();
    }
    public void S000_CALL_BPZSMUTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_P_GET_FEE, BPCSMUTF);
        if (BPCSMUTF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMUTF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_OUT_TS() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
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
