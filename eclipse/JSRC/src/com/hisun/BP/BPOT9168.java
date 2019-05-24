package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9168 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP077";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_ITM = 0;
    double WS_FEE_AMT = 0;
    short WS_CNT = 0;
    String WS_REG_CODE = " ";
    short WS_F_ITM_CNT = 0;
    short WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCONESF BPCONESF = new BPCONESF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCUFCAL BPCUFCAL = new BPCUFCAL();
    BPVFBAS BPVFBAS = new BPVFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    SCCGWA SCCGWA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    BPB9120_AWA_9120 BPB9120_AWA_9120;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9168 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCONESF);
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9120_AWA_9120>");
        BPB9120_AWA_9120 = (BPB9120_AWA_9120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.SVR_CD);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CHNL_NO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.BK_ACCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.PRDT_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.TXN_CCY);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CI_NO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CHG_ACNO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.FEE_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.TXN_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.TX_CNT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CHG_CCY);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.STR_DT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.MAT_DT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.PRC_METH);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.INT_BAS);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REL_CTNO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REL_CTTP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.AMT_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REL_PDTP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.AMT_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.AGGR_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_CCY);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_MTH);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[1-1].REF_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[1-1].REF_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[1-1].FEE_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[2-1].REF_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[2-1].REF_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[2-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[2-1].FEE_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[3-1].REF_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[3-1].REF_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[3-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[3-1].FEE_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[4-1].REF_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[4-1].REF_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[4-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[4-1].FEE_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[5-1].REF_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[5-1].REF_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[5-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REF_DATA[5-1].FEE_PCT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.FROM_FLG);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.TX_CD);
        B010_CHECK_INPUT();
        B015_GET_FEE_BASIC_INFO();
        B025_GET_BASIC_FEE_CNTR();
        B035_GET_FAV_INFO();
        B040_CAL_FEE_AMT();
        B090_OUTPUT_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPVFBAS;
        BPCTFBAS.INFO.REC_LEN = 258;
        BPVFBAS.KEY.FEE_CODE = BPB9120_AWA_9120.FEE_TYP;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPVFBAS.KEY);
        CEP.TRC(SCCGWA, BPVFBAS.FEE_NO);
        S000_CALL_BPZTFBAS();
        CEP.TRC(SCCGWA, BPCTFBAS.RETURN_INFO);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "ENTER NOTFND ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_CNT = BPVFBAS.FEE_NO;
        if (BPVFBAS.REG_TYPE.equalsIgnoreCase("00")) {
            WS_REG_CODE = " ";
        } else {
            if (BPVFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                WS_REG_CODE = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = WS_REG_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_REG_CODE = "0" + WS_REG_CODE;
            } else {
                R000_GET_REG_CODE_BY_TYPE();
            }
        }
    }
    public void B025_GET_BASIC_FEE_CNTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFCAL);
        BPCUFCAL.DATA.TX_AC = BPB9120_AWA_9120.CHG_ACNO;
        BPCUFCAL.DATA.FEE_NO = WS_CNT;
        BPCUFCAL.DATA.ACCT_CENTRE = BPB9120_AWA_9120.BK_ACCT;
        BPCUFCAL.DATA.CHNL_NO = BPB9120_AWA_9120.CHNL_NO;
        BPCUFCAL.DATA.TX_AMT = BPB9120_AWA_9120.TXN_AMT;
        BPCUFCAL.DATA.TX_CNT = BPB9120_AWA_9120.TX_CNT;
        BPCUFCAL.DATA.CNT_CCY = BPB9120_AWA_9120.TXN_CCY;
        BPCUFCAL.DATA.FEE_CCY = BPB9120_AWA_9120.REF_CCY;
        BPCUFCAL.DATA.CHG_CCY = BPB9120_AWA_9120.CHG_CCY;
        BPCUFCAL.DATA.EXG_FLG = BPVFBAS.EXG_FLG;
        BPCUFCAL.DATA.FROM_FLG = BPB9120_AWA_9120.FROM_FLG;
        BPCUFCAL.DATA.FEE_CODE = BPB9120_AWA_9120.FEE_TYP;
        BPCUFCAL.DATA.START_DATE = BPB9120_AWA_9120.STR_DT;
        BPCUFCAL.DATA.END_DATE = BPB9120_AWA_9120.MAT_DT;
        BPCUFCAL.DATA.REFER_MTH = BPB9120_AWA_9120.REF_MTH;
        BPCUFCAL.DATA.PRICE_MTH = BPB9120_AWA_9120.PRC_METH;
        BPCUFCAL.DATA.INT_BAS = BPB9120_AWA_9120.INT_BAS;
        BPCUFCAL.DATA.REL_CTRT_NO = BPB9120_AWA_9120.REL_CTNO;
        BPCUFCAL.DATA.R_CT_TYP = BPB9120_AWA_9120.REL_CTTP;
        BPCUFCAL.DATA.AMT_TYPE = BPB9120_AWA_9120.AMT_TYP;
        BPCUFCAL.DATA.R_PR_TYP = BPB9120_AWA_9120.REL_PDTP;
        BPCUFCAL.DATA.MULTI = BPB9120_AWA_9120.AMT_PCT;
        BPCUFCAL.DATA.AGGR_TYPE = BPB9120_AWA_9120.AGGR_TYP;
        BPCUFCAL.DATA.REF_CCY = BPB9120_AWA_9120.REF_CCY;
        CEP.TRC(SCCGWA, "23");
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            CEP.TRC(SCCGWA, WS_IDX);
            BPCUFCAL.DATA.FEE_DATA[WS_IDX-1].UP_AMT = BPB9120_AWA_9120.REF_DATA[WS_IDX-1].REF_AMT;
            BPCUFCAL.DATA.FEE_DATA[WS_IDX-1].UP_PCT = BPB9120_AWA_9120.REF_DATA[WS_IDX-1].REF_PCT;
            BPCUFCAL.DATA.FEE_DATA[WS_IDX-1].UP_CNT = 0;
            BPCUFCAL.DATA.FEE_DATA[WS_IDX-1].REF_FEE_AMT = BPB9120_AWA_9120.REF_DATA[WS_IDX-1].FEE_AMT;
            BPCUFCAL.DATA.FEE_DATA[WS_IDX-1].REF_FEE_PER = BPB9120_AWA_9120.REF_DATA[WS_IDX-1].FEE_PCT;
        }
        BPCUFCAL.DATA.CI_NO = BPB9120_AWA_9120.CI_NO;
        BPCUFCAL.DATA.FEE_CODE = BPB9120_AWA_9120.FEE_TYP;
        BPCUFCAL.DATA.REL_TX = BPB9120_AWA_9120.TX_CD;
        S000_CALL_BPZUFCAL();
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_AMT);
    }
    public void B035_GET_FAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPVFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCUFCAL.DATA.CHG_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPB9120_AWA_9120.REF_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPB9120_AWA_9120.PRDT_TYP;
        BPCTIFAV.INPUT_AREA.TX_AC = BPB9120_AWA_9120.CHG_ACNO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPB9120_AWA_9120.CI_NO;
        S000_CALL_BPZPIFAV();
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
    }
    public void B040_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_AMT);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_CCY);
        BPCONESF.FEEE_CCY = BPCUFCAL.DATA.FEE_CCY;
        CEP.TRC(SCCGWA, "ENTER SAME CCY");
        WS_FEE_AMT = BPCUFCAL.DATA.CHG_AMT;
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            WS_FEE_AMT = BPCUFCAL.DATA.CHG_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT;
        }
        BPCONESF.FEE_AMT = WS_FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        WS_FEE_AMT = BPCONESF.FEE_AMT;
        BPCONESF.CHG_AMT = BPCONESF.FEE_AMT;
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCONESF;
        SCCFMT.DATA_LEN = 162;
        CEP.TRC(SCCGWA, "OUT PUT DATA:");
        CEP.TRC(SCCGWA, BPCONESF.CHG_FLG);
        CEP.TRC(SCCGWA, BPCONESF.FEE_CODE);
        CEP.TRC(SCCGWA, BPCONESF.FEEE_CCY);
        CEP.TRC(SCCGWA, BPCONESF.FEE_BAS);
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AC);
        CEP.TRC(SCCGWA, BPCONESF.CHG_CCY);
        CEP.TRC(SCCGWA, BPCONESF.CHG_BAS);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AMT);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_REG_CODE_BY_TYPE() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_COM_FEE_INFO, BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RC.RC_CODE);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUFCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-F-CAL-STD-FEE", BPCUFCAL);
    }
    public void S000_CALL_BPZPIFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_IFAV, BPCTIFAV);
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