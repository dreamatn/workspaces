package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZECLRP {
    DBParm PSTEINF_RD;
    DBParm PSTPBIN_RD;
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE = "NETCLDR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    String WS_BRAC = " ";
    PSZECLRP_WS_AMT WS_AMT = new PSZECLRP_WS_AMT();
    char WS_TABLE_FLG = ' ';
    char WS_EXG_REC_STS = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICPUITM AICPUITM = new AICPUITM();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCECLRP PSCECLRP;
    public void MP(SCCGWA SCCGWA, PSCECLRP PSCECLRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCECLRP = PSCECLRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZECLRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_CHECK_TRAN_DATA();
        if (pgmRtn) return;
        B210_CHECK_AMT_COUNT();
        if (pgmRtn) return;
        B220_AMT_SQUARE_UP();
        if (pgmRtn) return;
        B400_WRITE_PSTIBLL();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCECLRP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.OUR_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.EXG_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.EXG_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCECLRP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCECLRP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCECLRP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        T000_READ_PSTEINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PSREINF.EXG_CLR_AC);
        if (PSREINF.EXG_CLR_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSREINF.EXG_CLR_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        }
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            CEP.TRC(SCCGWA, "ERR AC-TYPE");
            CEP.ERR(SCCGWA, PSCMSG_ERROR_MSG.PS_AC_TYP_NOT_ALLOW);
        }
        if (PSREINF.EXG_SYS_STS.equalsIgnoreCase("01") 
            || PSREINF.EXG_SYS_STS.equalsIgnoreCase("02") 
            || PSREINF.EXG_SYS_STS.equalsIgnoreCase("04")) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRT_NOT_ALLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.PB_IBCR_AMT == 0 
            && PSCECLRP.PB_IBCR_COUNT == 0 
            && PSCECLRP.PB_IBDR_AMT == 0 
            && PSCECLRP.PB_IBDR_COUNT == 0 
            && PSCECLRP.PB_OBCR_AMT == 0 
            && PSCECLRP.PB_OBCR_COUNT == 0 
            && PSCECLRP.PB_OBDR_AMT == 0 
            && PSCECLRP.PB_OBDR_COUNT == 0 
            && PSCECLRP.OUR_IBCR_AMT == 0 
            && PSCECLRP.OUR_IBCR_COUNT == 0 
            && PSCECLRP.OUR_IBDR_AMT == 0 
            && PSCECLRP.OUR_IBDR_COUNT == 0 
            && PSCECLRP.OUR_OBCR_AMT == 0 
            && PSCECLRP.OUR_OBCR_COUNT == 0 
            && PSCECLRP.OUR_OBDR_AMT == 0 
            && PSCECLRP.OUR_OBDR_COUNT == 0) {
            IBS.init(SCCGWA, PSREINF);
            PSREINF.KEY.EXG_BK_NO = PSCECLRP.BK_NO;
            PSREINF.KEY.EXG_AREA_NO = PSCECLRP.EXG_AREA_NO;
            PSREINF.KEY.EXG_CCY = PSCECLRP.EXG_CCY;
            PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            PSTEINF_RD = new DBParm();
            PSTEINF_RD.TableName = "PSTEINF";
            PSTEINF_RD.upd = true;
            IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
            PSREINF.EXG_SYS_STS = "03";
            PSTEINF_RD = new DBParm();
            PSTEINF_RD.TableName = "PSTEINF";
            IBS.REWRITE(SCCGWA, PSREINF, PSTEINF_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_OBLL_REC_EXIST;
                CEP.TRC(SCCGWA, WS_TABLE_FLG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTEINF";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
                B_DB_EXCP();
                if (pgmRtn) return;
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_CHECK_AMT_COUNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSCECLRP.PB_IBCR_AMT);
        CEP.TRC(SCCGWA, PSCECLRP.OUR_IBCR_AMT);
        if (PSCECLRP.PB_IBCR_AMT == PSCECLRP.OUR_IBCR_AMT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POIC_AMT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.PB_IBCR_COUNT == PSCECLRP.OUR_IBCR_COUNT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POIC_CNT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.PB_IBDR_AMT == PSCECLRP.OUR_IBDR_AMT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POID_AMT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.OUR_IBDR_COUNT == PSCECLRP.PB_IBDR_COUNT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POID_CNT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCECLRP.PB_OBCR_AMT);
        CEP.TRC(SCCGWA, PSCECLRP.OUR_OBCR_AMT);
        if (PSCECLRP.PB_OBCR_AMT == PSCECLRP.OUR_OBCR_AMT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POOC_AMT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCECLRP.PB_OBCR_COUNT);
        CEP.TRC(SCCGWA, PSCECLRP.OUR_OBCR_COUNT);
        if (PSCECLRP.PB_OBCR_COUNT == PSCECLRP.OUR_OBCR_COUNT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POOC_CNT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.PB_OBDR_AMT == PSCECLRP.OUR_OBDR_AMT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POOD_AMT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCECLRP.PB_OBDR_COUNT == PSCECLRP.OUR_OBDR_COUNT) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_POOD_CNT_MST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B220_AMT_SQUARE_UP() throws IOException,SQLException,Exception {
        WS_AMT.WS_REVALE_AMT = PSCECLRP.OUR_IBCR_AMT + PSCECLRP.OUR_OBDR_AMT;
        CEP.TRC(SCCGWA, WS_AMT.WS_REVALE_AMT);
        WS_AMT.WS_HANDLE_AMT = PSCECLRP.OUR_IBDR_AMT + PSCECLRP.OUR_OBCR_AMT;
        CEP.TRC(SCCGWA, WS_AMT.WS_HANDLE_AMT);
        if (WS_AMT.WS_REVALE_AMT > WS_AMT.WS_HANDLE_AMT) {
            WS_AMT.WS_EXG_AMT = WS_AMT.WS_REVALE_AMT - WS_AMT.WS_HANDLE_AMT;
        }
        if (WS_AMT.WS_REVALE_AMT < WS_AMT.WS_HANDLE_AMT) {
            WS_AMT.WS_EXG_AMT = WS_AMT.WS_HANDLE_AMT - WS_AMT.WS_REVALE_AMT;
        }
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCECLRP.EXG_CCY;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = WS_AMT.WS_REVALE_AMT;
        BPCPOEWA.DATA.AMT_INFO[4-1].AMT = WS_AMT.WS_HANDLE_AMT;
        if (WS_AMT.WS_REVALE_AMT < WS_AMT.WS_HANDLE_AMT) {
            BPCPOEWA.DATA.EVENT_CODE = "NETCLCR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "NETCLDR";
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        if (WS_AMT.WS_REVALE_AMT == WS_AMT.WS_HANDLE_AMT) {
        } else {
            B211_AI_FUAC_TALLY();
            if (pgmRtn) return;
        }
    }
    public void B211_AI_FUAC_TALLY() throws IOException,SQLException,Exception {
        if (WS_AMT.WS_REVALE_AMT > WS_AMT.WS_HANDLE_AMT) {
            IBS.init(SCCGWA, IBCPOSTA);
            IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBCPOSTA.AC = PSREINF.EXG_CLR_AC;
            IBCPOSTA.CCY = PSCECLRP.EXG_CCY;
            IBCPOSTA.AMT = WS_AMT.WS_EXG_AMT;
            IBCPOSTA.TXTYPE = "01";
            IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBCPOSTA.ENTRY_FLG = '1';
            IBCPOSTA.SIGN = 'D';
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, IBCPOSTA);
            IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBCPOSTA.AC = PSREINF.EXG_CLR_AC;
            IBCPOSTA.CCY = PSCECLRP.EXG_CCY;
            IBCPOSTA.AMT = WS_AMT.WS_EXG_AMT;
            IBCPOSTA.TXTYPE = "01";
            IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBCPOSTA.ENTRY_FLG = '1';
            IBCPOSTA.SIGN = 'C';
            S000_CALL_IBZCRAC();
            if (pgmRtn) return;
        }
    }
    public void B400_WRITE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCECLRP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCECLRP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCECLRP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.upd = true;
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_OBLL_REC_EXIST;
            CEP.TRC(SCCGWA, WS_TABLE_FLG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTEINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        PSREINF.EXG_SYS_STS = "03";
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.REWRITE(SCCGWA, PSREINF, PSTEINF_RD);
    }
    public void T000_READ_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, 0);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T100_READ_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        IBS.READ(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
