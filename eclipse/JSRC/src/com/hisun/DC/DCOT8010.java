package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT8010 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSHLDR DCCSHLDR = new DCCSHLDR();
    SCCGWA SCCGWA;
    DDB8010_AWA_8010 DDB8010_AWA_8010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT8010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8010_AWA_8010>");
        DDB8010_AWA_8010 = (DDB8010_AWA_8010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_HIS_HLD_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.AC);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.HLD_NO);
        if (DDB8010_AWA_8010.AC.trim().length() == 0 
            && DDB8010_AWA_8010.HLD_NO.trim().length() == 0 
            && DDB8010_AWA_8010.WRIT_NO.trim().length() == 0) {
            WS_CNT = WS_CNT + 1;
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_OR_HLDNO_MST_INP;
            if (DDB8010_AWA_8010.AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB8010_AWA_8010.AC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB8010_AWA_8010.FO_DATE == 0) {
            if ("00000000".trim().length() == 0) DDB8010_AWA_8010.FO_DATE = 0;
            else DDB8010_AWA_8010.FO_DATE = Integer.parseInt("00000000");
        }
        if (DDB8010_AWA_8010.TO_DATE == 0) {
            if ("99991231".trim().length() == 0) DDB8010_AWA_8010.TO_DATE = 0;
            else DDB8010_AWA_8010.TO_DATE = Integer.parseInt("99991231");
        }
        if (WS_CNT > 0) {
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B020_INQ_HIS_HLD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.BV_NO);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.HLD_NO);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.FO_DATE);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.TO_DATE);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.SPRBR_NM);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.WRIT_NO);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.CCY);
        CEP.TRC(SCCGWA, DDB8010_AWA_8010.CCY_TYPE);
        IBS.init(SCCGWA, DCCSHLDR);
        DCCSHLDR.AC = DDB8010_AWA_8010.AC;
        DCCSHLDR.SEQ = DDB8010_AWA_8010.SEQ;
        DCCSHLDR.BV_NO = DDB8010_AWA_8010.BV_NO;
        DCCSHLDR.HLD_NO = DDB8010_AWA_8010.HLD_NO;
        DCCSHLDR.FO_AC_DATE = DDB8010_AWA_8010.FO_DATE;
        DCCSHLDR.TO_AC_DATE = DDB8010_AWA_8010.TO_DATE;
        DCCSHLDR.SPRBR_NM = DDB8010_AWA_8010.SPRBR_NM;
        DCCSHLDR.WRIT_NO = DDB8010_AWA_8010.WRIT_NO;
        DCCSHLDR.CCY = DDB8010_AWA_8010.CCY;
        DCCSHLDR.CCY_TYPE = DDB8010_AWA_8010.CCY_TYPE;
        if (DDB8010_AWA_8010.FUNC == '1') {
            CEP.TRC(SCCGWA, "---FUNC=1---");
            S000_CALL_DCZSHLDR();
        }
        if (DDB8010_AWA_8010.FUNC == '2') {
            CEP.TRC(SCCGWA, "---FUNC=2---");
            S000_CALL_DCZSFLDR();
        }
        if (DDB8010_AWA_8010.FUNC == '3') {
            CEP.TRC(SCCGWA, "---FUNC=3---");
            S000_CALL_DCZSDLDR();
        }
    }
    public void S000_CALL_DCZSHLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZSHLDR", DCCSHLDR);
    }
    public void S000_CALL_DCZSFLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSFLDR", DCCSHLDR);
    }
    public void S000_CALL_DCZSDLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSDLDR", DCCSHLDR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
