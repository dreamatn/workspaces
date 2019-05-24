package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT9994 {
    String K_OUTPUT_FMT = "DC994";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSCLAC DDCSCLAC = new DDCSCLAC();
    DCCO9994 DCCO9994 = new DCCO9994();
    SCCGWA SCCGWA;
    DCB9994_AWA_9994 DCB9994_AWA_9994;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT9994 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB9994_AWA_9994>");
        DCB9994_AWA_9994 = (DCB9994_AWA_9994) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
        B300_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.CARD_NO);
        if (DCB9994_AWA_9994.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING;
            if (DCB9994_AWA_9994.CARD_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCB9994_AWA_9994.CARD_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB9994_AWA_9994.HD_IDTYP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CUS_TYP_MISSING;
            if (DCB9994_AWA_9994.HD_IDTYP.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCB9994_AWA_9994.HD_IDTYP);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB9994_AWA_9994.HD_IDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CUS_IDNO_MISSING;
            if (DCB9994_AWA_9994.HD_IDNO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCB9994_AWA_9994.HD_IDNO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.CARD_NO);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.HD_IDTYP);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.HD_IDNO);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.HD_CINO);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.HD_NAME);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.CARD_PSW);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.TO_BVTYP);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.TO_AC);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.TRF_FLG);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.NARRATIV);
        CEP.TRC(SCCGWA, DCB9994_AWA_9994.REMARK);
        IBS.init(SCCGWA, DDCSCLAC);
        DDCSCLAC.CLVR_FLG = '1';
        DDCSCLAC.DR_CARD = DCB9994_AWA_9994.CARD_NO;
        DDCSCLAC.AC_NO = DCB9994_AWA_9994.CARD_NO;
        DDCSCLAC.HOLDER_IDTYP = DCB9994_AWA_9994.HD_IDTYP;
        DDCSCLAC.HOLDER_IDNO = DCB9994_AWA_9994.HD_IDNO;
        DDCSCLAC.HOLDER_CINO = DCB9994_AWA_9994.HD_CINO;
        DDCSCLAC.HOLDER_NAME = DCB9994_AWA_9994.HD_NAME;
        DDCSCLAC.DRW_PSW = DCB9994_AWA_9994.CARD_PSW;
        DDCSCLAC.TO_BVTYP = DCB9994_AWA_9994.TO_BVTYP;
        DDCSCLAC.TRF_AC = DCB9994_AWA_9994.TO_AC;
        DDCSCLAC.TRF_FLG = DCB9994_AWA_9994.TRF_FLG;
        DDCSCLAC.NARRATIVE = DCB9994_AWA_9994.NARRATIV;
        DDCSCLAC.REMARK = DCB9994_AWA_9994.REMARK;
        DDCSCLAC.CCY = "156";
        DDCSCLAC.CCY_TYPE = '1';
        DDCSCLAC.BV_TYP = '1';
        S000_CALL_DDZSCLAC();
    }
    public void B300_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCO9994);
        DCCO9994.CARD_NO = DDCSCLAC.DR_CARD;
        DCCO9994.HOLDER_IDTYP = DDCSCLAC.HOLDER_IDTYP;
        DCCO9994.HOLDER_IDNO = DDCSCLAC.HOLDER_IDNO;
        DCCO9994.HOLDER_CINO = DDCSCLAC.HOLDER_CINO;
        DCCO9994.HOLDER_NAME = DDCSCLAC.HOLDER_NAME;
        DCCO9994.CARD_PSW = DDCSCLAC.DRW_PSW;
        DCCO9994.TO_BVTYP = DDCSCLAC.TO_BVTYP;
        DCCO9994.TO_AC = DDCSCLAC.TRF_AC;
        DCCO9994.TRF_FLG = DDCSCLAC.TRF_FLG;
        DCCO9994.NARRATIV = DDCSCLAC.NARRATIVE;
        DCCO9994.REMARK = DDCSCLAC.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCO9994;
        SCCFMT.DATA_LEN = 441;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZSCLAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CLOSE-AC", DDCSCLAC);
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
