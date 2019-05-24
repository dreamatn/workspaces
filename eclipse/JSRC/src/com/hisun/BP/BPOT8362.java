package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8362 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    int K_MAX_CNT = 20;
    int WS_GROUP_CNT = 0;
    String WS_ERR_MSG = " ";
    BPOT8362_WS_OUT_DATE WS_OUT_DATE = new BPOT8362_WS_OUT_DATE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCEX BPCEX = new BPCEX();
    BPCTENOR BPCTENOR = new BPCTENOR();
    BPREXRT BPREXRT1 = new BPREXRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCMT BPCMT = new BPCMT();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB8362_AWA_8362 BPB8362_AWA_8362;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8362 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8362_AWA_8362>");
        BPB8362_AWA_8362 = (BPB8362_AWA_8362) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        WS_OUT_DATE.WS_ERR_FLG = 'N';
        WS_GROUP_CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_MAIN_PROC();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.FUNC = 'A';
        BPCMT.DATA.EXR_TYPE = BPB8362_AWA_8362.TRCODE;
        BPCMT.DATA.EFF_DT = BPB8362_AWA_8362.CCY_INFO[1-1].DAY;
        CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[1-1].DAY);
        CEP.TRC(SCCGWA, BPCMT.DATA.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCMT.DATA.EFF_DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_ERROR;
            S000_ERR_MSG_PROC();
        }
        BPCMT.DATA.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCMT.DATA.IPT_DT = BPB8362_AWA_8362.CCY_INFO[1-1].DAY;
        BPCMT.DATA.IPT_TM = SCCGWA.COMM_AREA.TR_TIME;
        CEP.TRC(SCCGWA, BPB8362_AWA_8362.CURRNUM);
        B011_03_EXR_TYPE_CHK();
        for (WS_OUT_DATE.WS_I = 1; WS_OUT_DATE.WS_I <= BPB8362_AWA_8362.CURRNUM 
            && WS_OUT_DATE.WS_I <= K_MAX_CNT; WS_OUT_DATE.WS_I += 1) {
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY);
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].EXGUNIT);
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].SBPRICE);
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].SSPRICE);
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CBPRICE);
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CSPRICE);
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY;
            if (BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CTPRICE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LOCMID_ERROR;
                S000_ERR_MSG_PROC();
            }
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].MID_RAT = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CTPRICE;
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].UNIT = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].EXGUNIT;
            if (BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].SBPRICE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SBPRICE_ERROR;
                S000_ERR_MSG_PROC();
            }
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].FX_BUY = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].SBPRICE;
            if (BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].SSPRICE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SSPRICE_ERROR;
                S000_ERR_MSG_PROC();
            }
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].FX_SELL = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].SSPRICE;
            if (BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CBPRICE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CBPRICE_ERROR;
                S000_ERR_MSG_PROC();
            }
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CS_BUY = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CBPRICE;
            if (BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CSPRICE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CSPRICE_ERROR;
                S000_ERR_MSG_PROC();
            }
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CS_SELL = BPB8362_AWA_8362.CCY_INFO[WS_OUT_DATE.WS_I-1].CSPRICE;
            WS_GROUP_CNT = WS_GROUP_CNT + 1;
        }
        if (WS_GROUP_CNT == BPB8362_AWA_8362.CURRNUM) {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CURNUM_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPB8362_AWA_8362.CURRNUM <= BPB8362_AWA_8362.ALLNUM) {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CURNUM_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        S00_CALL_BPZMT();
    }
    public void B011_03_EXR_TYPE_CHK() throws IOException,SQLException,Exception {
        if (BPB8362_AWA_8362.TRCODE.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB8362_AWA_8362.TRCODE);
            IBS.init(SCCGWA, BPREXRT1);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT1.KEY.TYP = "EXRT";
            BPREXRT1.KEY.CD = BPB8362_AWA_8362.TRCODE;
            BPCPRMR.DAT_PTR = BPREXRT1;
            S000_CALL_BPZPRMR();
            CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
