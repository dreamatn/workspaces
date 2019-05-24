package com.hisun.PS;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZUQRYD {
    DBParm PSTIBLL_RD;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS821";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    PSZUQRYD_WS_FMT WS_FMT = new PSZUQRYD_WS_FMT();
    char WS_TABLE_FLG = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    BPCCCKWD BPCCCKWD = new BPCCCKWD();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSRIBLL PSRIBLL = new PSRIBLL();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCUQRYD PSCUQRYD;
    public void MP(SCCGWA SCCGWA, PSCUQRYD PSCUQRYD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCUQRYD = PSCUQRYD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZUQRYD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHEK_INPUTE_DATA();
        B200_READ_TABLE_PSTIBLL();
        B300_OUTPUT_PROC();
    }
    public void B100_CHEK_INPUTE_DATA() throws IOException,SQLException,Exception {
        if (PSCUQRYD.EXG_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRDT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCUQRYD.EXG_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRTMS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCUQRYD.EXG_JRNNO == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_JRN_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_READ_TABLE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCUQRYD.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCUQRYD.EXG_AREA_NO;
        PSRIBLL.KEY.EXG_DT = PSCUQRYD.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSCUQRYD.EXG_TMS;
        PSRIBLL.KEY.EXG_JRN_NO = PSCUQRYD.EXG_JRNNO;
        PSTIBLL_RD = new DBParm();
        PSTIBLL_RD.TableName = "PSTIBLL";
        IBS.READ(SCCGWA, PSRIBLL, PSTIBLL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
            CEP.TRC(SCCGWA, "FOUND");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PSTIBLL_NOFUNT_ERR;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTIBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_EXG_DT = PSRIBLL.KEY.EXG_DT;
        WS_FMT.WS_EXG_TMS = PSRIBLL.KEY.EXG_TMS;
        WS_FMT.WS_EXG_JRN_NO = PSRIBLL.KEY.EXG_JRN_NO;
        WS_FMT.WS_OUR_EXG_NO = PSRIBLL.OUR_EXG_NO;
        WS_FMT.WS_EXG_CCY = PSRIBLL.EXG_CCY;
        WS_FMT.WS_OTH_EXG_NO = PSRIBLL.OTH_EXG_NO;
        WS_FMT.WS_EXG_DC = PSRIBLL.EXG_DC;
        WS_FMT.WS_EXG_VOUCH_CD = PSRIBLL.EXG_VOUCH_CD;
        WS_FMT.WS_EXG_CERT_NO = PSRIBLL.EXG_CERT_NO;
        WS_FMT.WS_EXG_REC_STS = PSRIBLL.EXG_REC_STS;
        WS_FMT.WS_EXG_AMT = PSRIBLL.EXG_AMT;
        WS_FMT.WS_EXG_TX_BR = PSRIBLL.EXG_TX_BR;
        WS_FMT.WS_OUR_ACNO = PSRIBLL.OUR_ACNO;
        WS_FMT.WS_OUR_ACNM = PSRIBLL.OUR_ACNM;
        WS_FMT.WS_OTH_BKNO = PSRIBLL.OTH_BKNO;
        WS_FMT.WS_OTH_ACNO = PSRIBLL.OTH_ACNO;
        WS_FMT.WS_OTH_ACNM = PSRIBLL.OTH_ACNM;
        WS_FMT.WS_RMK = PSRIBLL.RMK;
        WS_FMT.WS_RET_RSN_CD = PSRIBLL.RET_RSN_CD;
        WS_FMT.WS_RET_RSN_DSC = PSRIBLL.RET_RSN_DSC;
        WS_FMT.WS_STR_CHNL = PSRIBLL.STR_CHNL;
        WS_FMT.WS_STR_JRN = PSRIBLL.STR_JRN;
        WS_FMT.WS_CRT_DATE = PSRIBLL.CRT_DATE;
        WS_FMT.WS_CRT_TLR = PSRIBLL.CRT_TLR;
        WS_FMT.WS_UPDTBL_DATE = PSRIBLL.UPDTBL_DATE;
        WS_FMT.WS_UPDTBL_TLR = PSRIBLL.UPDTBL_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 1011;
        IBS.FMT(SCCGWA, SCCFMT);
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
