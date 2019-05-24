package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4270 {
    String K_FMT_CD = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4270_WS_FMT_DATA WS_FMT_DATA = new BPOT4270_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    BPB4270_AWA_4270 BPB4270_AWA_4270;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4270 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4270_AWA_4270>");
        BPB4270_AWA_4270 = (BPB4270_AWA_4270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_DATA();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4270_AWA_4270.CCY);
        CEP.TRC(SCCGWA, BPB4270_AWA_4270.DATE1);
        CEP.TRC(SCCGWA, BPB4270_AWA_4270.DATE2);
        CEP.TRC(SCCGWA, BPB4270_AWA_4270.DAYS);
        CEP.TRC(SCCGWA, BPB4270_AWA_4270.WDAYS);
        CEP.TRC(SCCGWA, "HSUN059CHECK");
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043") 
            && BPB4270_AWA_4270.CALEN.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB4270_AWA_4270.CALEN);
            BPCOCLWD.CAL_CODE = BPB4270_AWA_4270.CALEN;
        } else {
            if (BPB4270_AWA_4270.CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_CD_MUST_INPUT;
                if (BPB4270_AWA_4270.CALEN.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPB4270_AWA_4270.CALEN);
                S000_ERR_MSG_PROC();
            } else {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB4270_AWA_4270.CCY;
                S000_CALL_BPZQCCY();
                BPCOCLWD.CAL_CODE = BPCQCCY.DATA.CAL_CD;
            }
        }
        BPCOCLWD.DATE1 = BPB4270_AWA_4270.DATE1;
        BPCOCLWD.DAYE_FLG = 'Y';
        BPCOCLWD.DATE2 = BPB4270_AWA_4270.DATE2;
        BPCOCLWD.DAYS = BPB4270_AWA_4270.DAYS;
        BPCOCLWD.WDAYS = BPB4270_AWA_4270.WDAYS;
        if (BPB4270_AWA_4270.DAYS == 0 
            && BPB4270_AWA_4270.DATE2 == 0 
            && BPB4270_AWA_4270.WDAYS == 0) {
            BPCOCLWD.DATE2 = BPB4270_AWA_4270.DATE1;
        }
        S000_CALL_BPZPCLWD();
    }
    public void B300_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_CCY = BPB4270_AWA_4270.CCY;
        WS_FMT_DATA.WS_FMT_DATE1 = BPCOCLWD.DATE1;
        WS_FMT_DATA.WS_FMT_DATE2 = BPCOCLWD.DATE2;
        WS_FMT_DATA.WS_FMT_DAYS = BPCOCLWD.DAYS;
        WS_FMT_DATA.WS_FMT_WDAYS = BPCOCLWD.WDAYS;
        WS_FMT_DATA.WS_FMT_HDAYS = BPCOCLWD.HDAYS;
        WS_FMT_DATA.WS_FMT_WEEK_NO1 = BPCOCLWD.WEEK_NO1;
        WS_FMT_DATA.WS_FMT_WEEK_NO2 = BPCOCLWD.WEEK_NO2;
        WS_FMT_DATA.WS_FMT_DATE1_FLG = BPCOCLWD.DATE1_FLG;
        WS_FMT_DATA.WS_FMT_DATE1_CHAR = BPCOCLWD.DATE1_CHAR;
        WS_FMT_DATA.WS_FMT_DATE2_FLG = BPCOCLWD.DATE2_FLG;
        WS_FMT_DATA.WS_FMT_DATE2_CHAR = BPCOCLWD.DATE2_CHAR;
        CEP.TRC(SCCGWA, BPB4270_AWA_4270.CCY);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.HDAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.WEEK_NO1);
        CEP.TRC(SCCGWA, BPCOCLWD.WEEK_NO2);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1_FLG);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1_CHAR);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2_FLG);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2_CHAR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 48;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void END PROGRAM BPOT4270() throws IOException,SQLException,Exception {
    public void IDENTIFICATION DIVISION() throws IOException,SQLException,Exception {
    public void PROGRAM_ID. BPOT4270() throws IOException,SQLException,Exception {
    public void AUTHOR. HSUN059() throws IOException,SQLException,Exception {
    public void DATE_WRITTEN. 2014 / 04 / 22() throws IOException,SQLException,Exception {
    public void ENVIRONMENT DIVISION() throws IOException,SQLException,Exception {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4270_WS_FMT_DATA WS_FMT_DATA = new BPOT4270_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    BPB4270_AWA_4270 BPB4270_AWA_4270;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
            this.SCCGWA = SCCGWA;
            CEP.TRC(SCCGWA);
            A000_INIT_PROC();
            B000_MAIN_PROC();
            CEP.TRC(SCCGWA, "BPOT4270 return!");
            Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4270_AWA_4270>");
        BPB4270_AWA_4270 = (BPB4270_AWA_4270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_DATA();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB4270_AWA_4270.CCY;
        S000_CALL_BPZQCCY();
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCQCCY.DATA.CAL_CD;
        BPCOCLWD.DATE1 = BPB4270_AWA_4270.DATE1;
        S000_CALL_BPZPCLWD();
    }
    public void B300_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        IBS.init(SCCGWA, BPCOCLWD);
        WS_FMT_DATA.WS_FMT_CCY = BPB4270_AWA_4270.CCY;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_CCY);
        WS_FMT_DATA.WS_FMT_DATE1 = BPCOCLWD.DATE1;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DATE1);
        WS_FMT_DATA.WS_FMT_DATE2 = BPCOCLWD.DATE2;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DATE2);
        WS_FMT_DATA.WS_FMT_DAYS = BPCOCLWD.DAYS;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DAYS);
        WS_FMT_DATA.WS_FMT_WDAYS = BPCOCLWD.WDAYS;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_WDAYS);
        WS_FMT_DATA.WS_FMT_HDAYS = BPCOCLWD.HDAYS;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_HDAYS);
        WS_FMT_DATA.WS_FMT_WEEK_NO1 = BPCOCLWD.WEEK_NO1;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_WEEK_NO1);
        WS_FMT_DATA.WS_FMT_WEEK_NO2 = BPCOCLWD.WEEK_NO2;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_WEEK_NO2);
        WS_FMT_DATA.WS_FMT_DATE1_FLG = BPCOCLWD.DATE1_FLG;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DATE1_FLG);
        WS_FMT_DATA.WS_FMT_DATE1_CHAR = BPCOCLWD.DATE1_CHAR;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DATE1_CHAR);
        WS_FMT_DATA.WS_FMT_DATE2_FLG = BPCOCLWD.DATE2_FLG;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DATE2_FLG);
        WS_FMT_DATA.WS_FMT_DATE2_CHAR = BPCOCLWD.DATE2_CHAR;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DATE2_CHAR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 48;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
