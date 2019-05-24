package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8361 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    int K_MAX_CNT = 20;
    String WS_ERR_MSG = " ";
    BPOT8361_WS_OUT_DATE WS_OUT_DATE = new BPOT8361_WS_OUT_DATE();
    BPCEX BPCEX = new BPCEX();
    BPCTENOR BPCTENOR = new BPCTENOR();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCMT BPCMT = new BPCMT();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB8361_AWA_8361 BPB8361_AWA_8361;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8361 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8361_AWA_8361>");
        BPB8361_AWA_8361 = (BPB8361_AWA_8361) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        WS_OUT_DATE.WS_ERR_FLG = 'N';
        CEP.TRC(SCCGWA, BPCRBANK.EX_RA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_MAIN_PROC();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.FUNC = 'A';
        BPCMT.DATA.EXR_TYPE = "TRE";
        BPCMT.DATA.EFF_DT = BPB8361_AWA_8361.CCY_INFO[1-1].DAY;
        BPCMT.DATA.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCMT.DATA.IPT_DT = BPB8361_AWA_8361.CCY_INFO[1-1].DAY;
        BPCMT.DATA.IPT_TM = SCCGWA.COMM_AREA.TR_TIME;
        CEP.TRC(SCCGWA, BPB8361_AWA_8361.CURRNUM);
        for (WS_OUT_DATE.WS_I = 1; WS_OUT_DATE.WS_I <= BPB8361_AWA_8361.CURRNUM 
            && WS_OUT_DATE.WS_I <= K_MAX_CNT; WS_OUT_DATE.WS_I += 1) {
            CEP.TRC(SCCGWA, BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY);
            CEP.TRC(SCCGWA, BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].EXGUNIT);
            CEP.TRC(SCCGWA, BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].SBPRICE);
            CEP.TRC(SCCGWA, BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].SSPRICE);
            CEP.TRC(SCCGWA, BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CBPRICE);
            CEP.TRC(SCCGWA, BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CSPRICE);
            B020_01_CHANGE_CCY();
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].MID_RAT = BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CTPRICE;
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].UNIT = BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].EXGUNIT;
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].FX_BUY = BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].SBPRICE;
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].FX_SELL = BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].SSPRICE;
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CS_BUY = BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CBPRICE;
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CS_SELL = BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CSPRICE;
        }
        S00_CALL_BPZMT();
    }
    public void B020_01_CHANGE_CCY() throws IOException,SQLException,Exception {
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("CNY")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "156";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("JPY")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "392";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("EUR")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "978";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("GBP")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "826";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("CHF")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "756";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("CAD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "124";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("AUD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "036";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("NZD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "554";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("HKD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "344";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("USD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "840";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("SGD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "702";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("MOP")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "446";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("IDR")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "360";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("KRW")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "410";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("MYR")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "458";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("THB")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "764";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("TWD")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "901";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("RUB")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "643";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("DKK")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "208";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("NOK")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "578";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("SEK")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "752";
        }
        if (BPB8361_AWA_8361.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY.equalsIgnoreCase("ZAR")) {
            BPCMT.DATA.CCY_INFO[WS_OUT_DATE.WS_I-1].CCY = "710";
        }
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT);
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
