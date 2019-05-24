package com.hisun.PS;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZRQRYD {
    DBParm PSTOBLL_RD;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS811";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    PSZRQRYD_WS_FMT WS_FMT = new PSZRQRYD_WS_FMT();
    char WS_TABLE_FLG = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    BPCCCKWD BPCCCKWD = new BPCCCKWD();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSROBLL PSROBLL = new PSROBLL();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCRQRYD PSCRQRYD;
    public void MP(SCCGWA SCCGWA, PSCRQRYD PSCRQRYD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCRQRYD = PSCRQRYD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZRQRYD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHEK_INPUTE_DATA();
        B200_READ_TABLE_PSTOBLL();
        B300_OUTPUT_PROC();
    }
    public void B100_CHEK_INPUTE_DATA() throws IOException,SQLException,Exception {
        if (PSCRQRYD.EXG_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TCDT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRQRYD.EXG_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TCTMS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRQRYD.EXG_JRNNO == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_JRN_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_READ_TABLE_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCRQRYD.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCRQRYD.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCRQRYD.EXG_DT;
        PSROBLL.KEY.EXG_TMS = PSCRQRYD.EXG_TMS;
        PSROBLL.KEY.EXG_JRN_NO = PSCRQRYD.EXG_JRNNO;
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
            CEP.TRC(SCCGWA, "FOUND");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PSTOBLL_NOFUNT_ERR;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTOBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_EXG_DT = PSROBLL.KEY.EXG_DT;
        WS_FMT.WS_EXG_TMS = PSROBLL.KEY.EXG_TMS;
        WS_FMT.WS_EXG_JRN_NO = PSROBLL.KEY.EXG_JRN_NO;
        WS_FMT.WS_TUI_JRN_NO = PSROBLL.RET_JRN_NO;
        WS_FMT.WS_OUR_EXG_NO = PSROBLL.OUR_EXG_NO;
        WS_FMT.WS_EXG_CCY = PSROBLL.EXG_CCY;
        WS_FMT.WS_OTH_EXG_NO = PSROBLL.OTH_EXG_NO;
        WS_FMT.WS_EXG_DC = PSROBLL.EXG_DC;
        WS_FMT.WS_EXG_VOUCH_CD = PSROBLL.EXG_VOUCH_CD;
        WS_FMT.WS_EXG_CERT_NO = PSROBLL.EXG_CERT_NO;
        WS_FMT.WS_EXG_REC_STS = PSROBLL.EXG_REC_STS;
        WS_FMT.WS_EXG_AMT = PSROBLL.EXG_AMT;
        WS_FMT.WS_EXG_TX_BR = PSROBLL.EXG_TX_BR;
        WS_FMT.WS_OUR_ACNO = PSROBLL.OUR_ACNO;
        WS_FMT.WS_OUR_ACNM = PSROBLL.OUR_ACNM;
        WS_FMT.WS_OTH_ACNO = PSROBLL.OTH_ACNO;
        WS_FMT.WS_OTH_ACNM = PSROBLL.OTH_ACNM;
        WS_FMT.WS_R_BILL_TYP = PSROBLL.R_BILL_TYP;
        WS_FMT.WS_CERT_DT = PSROBLL.CERT_DT;
        WS_FMT.WS_ISS_BKNO = PSROBLL.ISS_BKNO;
        WS_FMT.WS_EXP_DT = PSROBLL.EXP_DT;
        WS_FMT.WS_ISS_AMT = PSROBLL.ISS_AMT;
        WS_FMT.WS_ENCRY_NO = PSROBLL.ENCRY_NO;
        WS_FMT.WS_CT_FLG = PSROBLL.CT_FLG;
        WS_FMT.WS_CREV_NO = PSROBLL.CREV_NO;
        WS_FMT.WS_CASH_ID = PSROBLL.CASH_ID;
        WS_FMT.WS_SHL_EXG_DT = PSROBLL.SHL_EXG_DT;
        WS_FMT.WS_SHL_EXG_TMS = PSROBLL.SHL_EXG_TMS;
        WS_FMT.WS_ACT_EXG_DT = PSROBLL.ACT_EXG_DT;
        WS_FMT.WS_ACT_EXG_TMS = PSROBLL.ACT_EXG_TMS;
        WS_FMT.WS_EXG_REPT_FLG = PSROBLL.EXG_REPT_FLG;
        WS_FMT.WS_RMK = PSROBLL.RMK;
        WS_FMT.WS_RET_RSN_CD = PSROBLL.RET_RSN_CD;
        WS_FMT.WS_RET_RSN_DSC = PSROBLL.RET_RSN_DSC;
        WS_FMT.WS_BILL_HLD_AC = PSROBLL.BILL_HLD_AC;
        WS_FMT.WS_BILL_HLD_ACNM = PSROBLL.BILL_HLD_ACNM;
        WS_FMT.WS_STR_CHNL = PSROBLL.STR_CHNL;
        WS_FMT.WS_STR_JRN = PSROBLL.STR_JRN;
        WS_FMT.WS_CRT_DATE = PSROBLL.CRT_DATE;
        WS_FMT.WS_CRT_TLR = PSROBLL.CRT_TLR;
        WS_FMT.WS_UPDTBL_DATE = PSROBLL.UPDTBL_DATE;
        WS_FMT.WS_UPDTBL_TLR = PSROBLL.UPDTBL_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 1404;
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
