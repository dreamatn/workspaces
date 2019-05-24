package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1431 {
    DBParm BPTBSP33_RD;
    String CPN_U_BPZSORGI = "BP-S-SORGI";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSORGI BPCSORGI = new BPCSORGI();
    BPRBSP33 BPRBSP33 = new BPRBSP33();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPB1430_AWA_1430 BPB1430_AWA_1430;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1431 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1430_AWA_1430>");
        BPB1430_AWA_1430 = (BPB1430_AWA_1430) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        if (BPB1430_AWA_1430.AC.trim().length() == 0) {
            CEP.ERRC(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPB1430_AWA_1430.AC_NO);
        }
        if (BPB1430_AWA_1430.NEW_BR == 0) {
            CEP.ERRC(SCCGWA, BPCMSG_ERROR_MSG.BP_VBBR_INPUT, BPB1430_AWA_1430.NEW_BR_NO);
        }
        if (BPB1430_AWA_1430.OLD_BR == 0) {
            CEP.ERRC(SCCGWA, BPCMSG_ERROR_MSG.BP_VBBR_INPUT, BPB1430_AWA_1430.OLD_BR_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B200_PNT_PROC() throws IOException,SQLException,Exception {
        B210_PNT_PROC();
    }
    public void B210_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSORGI);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.TX_SEQ);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.AC);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.AC_TYP);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.CCY);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.SEQ);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.OLD_BR);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.NEW_BR);
        CEP.TRC(SCCGWA, BPB1430_AWA_1430.INCO_DT);
        BPCSORGI.DATA.TX_SEQ = BPB1430_AWA_1430.TX_SEQ;
        BPCSORGI.DATA.OLD_BR = BPB1430_AWA_1430.OLD_BR;
        BPCSORGI.DATA.NEW_BR = BPB1430_AWA_1430.NEW_BR;
        BPCSORGI.DATA.ORG_TYP = '2';
        BPCSORGI.DATA.AC = BPB1430_AWA_1430.AC;
        BPCSORGI.DATA.AC_TYP = BPB1430_AWA_1430.AC_TYP;
        BPCSORGI.DATA.CCY = BPB1430_AWA_1430.CCY;
        BPCSORGI.DATA.CCY_TYPE = BPB1430_AWA_1430.CCY_TYPE;
        BPCSORGI.DATA.SEQ = BPB1430_AWA_1430.SEQ;
        BPCSORGI.DATA.INCO_DT = BPB1430_AWA_1430.INCO_DT;
        BPCSORGI.DATA.ACO_AC = BPB1430_AWA_1430.ACO_AC;
        T000_READ_BPTBSP33();
        CEP.TRC(SCCGWA, BPRBSP33.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, BPRBSP33.BR);
        CEP.TRC(SCCGWA, BPRBSP33.TLT);
        BPCSORGI.DATA.TX_JRN = BPRBSP33.KEY.AP_BATNO;
        BPCSORGI.DATA.TR_BRANCH = BPRBSP33.BR;
        BPCSORGI.DATA.TR_TLR = BPRBSP33.TLT;
        CEP.TRC(SCCGWA, BPCSORGI.DATA.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.TR_TLR);
        BPCSORGI.DATA.FUNC = 'P';
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.NEW_BR);
        S000_CALL_BPZSORGI();
    }
    public void T000_READ_BPTBSP33() throws IOException,SQLException,Exception {
        BPTBSP33_RD = new DBParm();
        BPTBSP33_RD.TableName = "BPTBSP33";
        BPTBSP33_RD.fst = true;
        BPTBSP33_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BPRBSP33, BPTBSP33_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZSORGI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZSORGI, BPCSORGI);
        if (BPCSORGI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSORGI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
