package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6560 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTRHIS_RD;
    BPOT6560_WS_INDEX WS_INDEX = new BPOT6560_WS_INDEX();
    BPOT6560_WS_INT_RATE_DATA[] WS_INT_RATE_DATA = new BPOT6560_WS_INT_RATE_DATA[999];
    BPOT6560_WS_CHECK_DATA WS_CHECK_DATA = new BPOT6560_WS_CHECK_DATA();
    char WS_ID = ' ';
    BPOT6560_WS_REC_DATA WS_REC_DATA = new BPOT6560_WS_REC_DATA();
    char WS_CHK_RATE = ' ';
    char WS_STOP_FLAG = ' ';
    char WS_RATE_ID_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSURTE BPCSURTE = new BPCSURTE();
    BPCEXCHK BPCEXCHK = new BPCEXCHK();
    BPCIRAT BPCIRAT = new BPCIRAT();
    BPRRHIS BPRRHIS = new BPRRHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6560_AWA_6560 BPB6560_AWA_6560;
    public BPOT6560() {
        for (int i=0;i<999;i++) WS_INT_RATE_DATA[i] = new BPOT6560_WS_INT_RATE_DATA();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6560 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6560_AWA_6560>");
        BPB6560_AWA_6560 = (BPB6560_AWA_6560) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (WS_INDEX.WS_RATE_CNT > 0) {
            B030_UPDATE_RATE_TABLE();
            B040_WRITE_ANS_HIS();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_STOP_FLAG = 'N';
        WS_REC_DATA.WS_START = 1;
        WS_REC_DATA.WS_LEN = 34;
        for (WS_INDEX.WS_I = 1; WS_INDEX.WS_I <= 255 
            && WS_STOP_FLAG != 'Y'; WS_INDEX.WS_I += 1) {
            IBS.init(SCCGWA, BPCIRAT);
            if (BPB6560_AWA_6560.RAT_DATA == null) BPB6560_AWA_6560.RAT_DATA = "";
            JIBS_tmp_int = BPB6560_AWA_6560.RAT_DATA.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPB6560_AWA_6560.RAT_DATA += " ";
            IBS.CPY2CLS(SCCGWA, BPB6560_AWA_6560.RAT_DATA.substring(WS_REC_DATA.WS_START - 1, WS_REC_DATA.WS_START + WS_REC_DATA.WS_LEN - 1), BPCIRAT);
            CEP.TRC(SCCGWA, "***** INPUT DATA *****");
            CEP.TRC(SCCGWA, WS_REC_DATA.WS_START);
            CEP.TRC(SCCGWA, BPCIRAT.DATA.CCY);
            CEP.TRC(SCCGWA, BPCIRAT.DATA.EFF_DATE);
            CEP.TRC(SCCGWA, BPCIRAT.DATA.ID);
            CEP.TRC(SCCGWA, BPCIRAT.DATA.PERIOD);
            CEP.TRC(SCCGWA, BPCIRAT.DATA.RATE);
            if (BPCIRAT.DATA.CCY.trim().length() == 0) {
                WS_STOP_FLAG = 'Y';
            } else {
                WS_INDEX.WS_REC_CNT = WS_INDEX.WS_REC_CNT + 1;
                WS_ID = BPCIRAT.DATA.ID;
                B020_CHK_REC_VALID();
                WS_REC_DATA.WS_START = (short) (WS_REC_DATA.WS_START + WS_REC_DATA.WS_LEN);
            }
        }
        if (WS_INDEX.WS_REC_CNT == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY);
        }
    }
    public void B020_CHK_REC_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK);
        IBS.init(SCCGWA, WS_CHECK_DATA);
        WS_CHECK_DATA.WS_CCY = BPCIRAT.DATA.CCY;
        WS_CHECK_DATA.WS_EFF_DATE = BPCIRAT.DATA.EFF_DATE;
        if (BPCIRAT.DATA.RATE.trim().length() == 0) WS_CHECK_DATA.WS_RATE = 0;
        else WS_CHECK_DATA.WS_RATE = Double.parseDouble(BPCIRAT.DATA.RATE);
        if (BPCIRAT.DATA.ID == '1') {
            WS_CHECK_DATA.WS_RATE_TYPE = "006";
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = "LIBOR" + WS_CHECK_DATA.WS_RATE_ID.substring(5);
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            if (BPCIRAT.DATA.CCY == null) BPCIRAT.DATA.CCY = "";
            JIBS_tmp_int = BPCIRAT.DATA.CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCIRAT.DATA.CCY += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 6 - 1) + BPCIRAT.DATA.CCY + WS_CHECK_DATA.WS_RATE_ID.substring(6 + 3 - 1);
            R000_SET_TENOR();
        } else if (BPCIRAT.DATA.ID == '2') {
            WS_CHECK_DATA.WS_RATE_TYPE = "005";
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = "HIBOR" + WS_CHECK_DATA.WS_RATE_ID.substring(5);
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            if (BPCIRAT.DATA.CCY == null) BPCIRAT.DATA.CCY = "";
            JIBS_tmp_int = BPCIRAT.DATA.CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCIRAT.DATA.CCY += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 6 - 1) + BPCIRAT.DATA.CCY + WS_CHECK_DATA.WS_RATE_ID.substring(6 + 3 - 1);
            R000_SET_TENOR();
        } else if (BPCIRAT.DATA.ID == '3') {
            WS_CHECK_DATA.WS_RATE_TYPE = "029";
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            if (BPCIRAT.DATA.CCY == null) BPCIRAT.DATA.CCY = "";
            JIBS_tmp_int = BPCIRAT.DATA.CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCIRAT.DATA.CCY += " ";
            WS_CHECK_DATA.WS_RATE_ID = BPCIRAT.DATA.CCY + WS_CHECK_DATA.WS_RATE_ID.substring(3);
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 4 - 1) + "029" + WS_CHECK_DATA.WS_RATE_ID.substring(4 + 3 - 1);
            WS_CHECK_DATA.WS_TENOR = "" + 301;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
        } else if (BPCIRAT.DATA.ID == '5') {
            WS_CHECK_DATA.WS_RATE_TYPE = "100";
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = "SHIBO" + WS_CHECK_DATA.WS_RATE_ID.substring(5);
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            if (BPCIRAT.DATA.CCY == null) BPCIRAT.DATA.CCY = "";
            JIBS_tmp_int = BPCIRAT.DATA.CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCIRAT.DATA.CCY += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 6 - 1) + BPCIRAT.DATA.CCY + WS_CHECK_DATA.WS_RATE_ID.substring(6 + 3 - 1);
            R000_SET_TENOR();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_ID_ERROR);
        }
        BPCEXCHK.EXCEL_TYPE = "05";
        BPCEXCHK.EXCEL_DATA = IBS.CLS2CPY(SCCGWA, WS_CHECK_DATA);
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA);
        S000_CALL_BPZEXCRT();
        if (BPCEXCHK.DATA_FLG == '0') {
            WS_CHK_RATE = 'F';
        } else {
            WS_INDEX.WS_RATE_CNT = WS_INDEX.WS_RATE_CNT + 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHECK_DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_INT_RATE_DATA[WS_INDEX.WS_RATE_CNT-1]);
        }
        CEP.TRC(SCCGWA, BPCEXCHK.DATA_FLG);
    }
    public void R000_SET_TENOR() throws IOException,SQLException,Exception {
        if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("ON ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" ON") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("SO") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" SO"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 1;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "ON" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("1W ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 1W") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("SS") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" SS"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 7;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "1W" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("SW ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" SW"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 7;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "1W" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("2W ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 2W") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("ST") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" ST"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 14;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "2W" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("1M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 1M") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("S1") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" S1"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 101;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "1M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("2M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 2M"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 102;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "2M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("3M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 3M") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("S3") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" S3"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 103;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "3M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("4M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 4M"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 104;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "4M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("5M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 5M"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 105;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "5M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("6M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 6M") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("S6") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" S6"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 106;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "6M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("7M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 7M"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 107;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "7M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("8M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 8M"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 108;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "8M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("9M ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 9M") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("S9") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" S9"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 109;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "9M" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else if (BPCIRAT.DATA.PERIOD.equalsIgnoreCase("10M")) {
            WS_CHECK_DATA.WS_TENOR = "" + 110;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 8 - 1) + "10M" + WS_CHECK_DATA.WS_RATE_ID.substring(8 + 3 - 1);
        } else if (BPCIRAT.DATA.PERIOD.equalsIgnoreCase("11M")) {
            WS_CHECK_DATA.WS_TENOR = "" + 111;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 8 - 1) + "11M" + WS_CHECK_DATA.WS_RATE_ID.substring(8 + 3 - 1);
        } else if ((BPCIRAT.DATA.PERIOD.equalsIgnoreCase("1Y ") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" 1Y") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase("SY") 
                || BPCIRAT.DATA.PERIOD.equalsIgnoreCase(" SY"))) {
            WS_CHECK_DATA.WS_TENOR = "" + 112;
            JIBS_tmp_int = WS_CHECK_DATA.WS_TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_TENOR = "0" + WS_CHECK_DATA.WS_TENOR;
            if (WS_CHECK_DATA.WS_RATE_ID == null) WS_CHECK_DATA.WS_RATE_ID = "";
            JIBS_tmp_int = WS_CHECK_DATA.WS_RATE_ID.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CHECK_DATA.WS_RATE_ID += " ";
            WS_CHECK_DATA.WS_RATE_ID = WS_CHECK_DATA.WS_RATE_ID.substring(0, 9 - 1) + "1Y" + WS_CHECK_DATA.WS_RATE_ID.substring(9 + 2 - 1);
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID);
        }
    }
    public void B030_UPDATE_RATE_TABLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INDEX.WS_RATE_CNT);
        for (WS_INDEX.WS_J = 1; WS_INDEX.WS_J <= WS_INDEX.WS_RATE_CNT; WS_INDEX.WS_J += 1) {
            IBS.init(SCCGWA, BPCSURTE);
            CEP.TRC(SCCGWA, WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_RATE_TYPE1);
            CEP.TRC(SCCGWA, WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_CCY1);
            CEP.TRC(SCCGWA, WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_TENOR1);
            CEP.TRC(SCCGWA, WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_EFF_DATE1);
            CEP.TRC(SCCGWA, WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_RATE1);
            BPCSURTE.BASE_TYP = WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_RATE_TYPE1;
            BPCSURTE.CCY = WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_CCY1;
            BPCSURTE.TENOR = WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_TENOR1;
            BPCSURTE.EFF_DT = WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_EFF_DATE1;
            BPCSURTE.RATE = WS_INT_RATE_DATA[WS_INDEX.WS_J-1].WS_RATE1;
            S000_CALL_BPZSURTE();
        }
    }
    public void B040_WRITE_ANS_HIS() throws IOException,SQLException,Exception {
        if (BPB6560_AWA_6560.MSG_CNT != 0 
            && BPB6560_AWA_6560.MSG_SEQ != 0 
            && BPB6560_AWA_6560.MSG_CNT == BPB6560_AWA_6560.MSG_SEQ) {
            IBS.init(SCCGWA, BPRRHIS);
            BPRRHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            BPRRHIS.KEY.TYPE = 'R';
            if (WS_ID == '1') {
                WS_RATE_ID_FLG = '1';
            } else if (WS_ID == '2') {
                WS_RATE_ID_FLG = '2';
            } else if (WS_ID == '3') {
                WS_RATE_ID_FLG = '3';
            } else if (WS_ID == '5') {
                WS_RATE_ID_FLG = '5';
            } else {
                WS_RATE_ID_FLG = '0';
            }
            BPRRHIS.REQ_TYPE = WS_RATE_ID_FLG;
            BPRRHIS.TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_INSERT_BPTRHIS();
        }
    }
    public void T000_INSERT_BPTRHIS() throws IOException,SQLException,Exception {
        BPRRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPTRHIS_RD = new DBParm();
        BPTRHIS_RD.TableName = "BPTRHIS";
        IBS.WRITE(SCCGWA, BPRRHIS, BPTRHIS_RD);
    }
    public void S000_CALL_BPZSURTE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-UPD-RATE", BPCSURTE);
        if (BPCSURTE.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSURTE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZEXCRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-RATE-CHK", BPCEXCHK);
        if (BPCEXCHK.RC.RC_CODE != 0 
            && BPCEXCHK.RC.RC_CODE != 9263) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
