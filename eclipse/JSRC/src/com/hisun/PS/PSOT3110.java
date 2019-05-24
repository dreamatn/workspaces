package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT3110 {
    int JIBS_tmp_int;
    brParm PSTOBLL_BR = new brParm();
    brParm PSTIBLL_BR = new brParm();
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS311";
    String CPN_U_PSZECLRP = "PS-P-CLR-PROC";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_READ_TRAN = ' ';
    String WS_EXG_AREA_NO = " ";
    PSOT3110_WS_AMT WS_AMT = new PSOT3110_WS_AMT();
    PSOT3110_WS_CONT WS_CONT = new PSOT3110_WS_CONT();
    PSOT3110_WS_FMT WS_FMT = new PSOT3110_WS_FMT();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB3110_AWA_3110 PSB3110_AWA_3110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        B300_OUTPUT_PROC();
        CEP.TRC(SCCGWA, "PSOT3110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB3110_AWA_3110>");
        PSB3110_AWA_3110 = (PSB3110_AWA_3110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CHEK_INPUT();
        T000_STARTBR_TRAN_PSTOBLL();
        T000_READNEXT_PSTOBLL();
        CEP.TRC(SCCGWA, PSROBLL.EXG_DC);
        while (WS_READ_TRAN != 'N') {
            if (PSROBLL.EXG_DC == 'C' 
                && PSROBLL.EXG_REC_STS == '1') {
                WS_AMT.WS_TC_C_AMT = WS_AMT.WS_TC_C_AMT + PSROBLL.EXG_AMT;
                WS_CONT.WS_TC_C_CONT = (short) (WS_CONT.WS_TC_C_CONT + 1);
            } else {
                if (PSROBLL.EXG_DC == 'D' 
                    && (PSROBLL.EXG_REC_STS == '2' 
                    || PSROBLL.EXG_VOUCH_CD.equalsIgnoreCase("98"))) {
                    WS_AMT.WS_TC_D_AMT = WS_AMT.WS_TC_D_AMT + PSROBLL.EXG_AMT;
                    WS_CONT.WS_TC_D_CONT = (short) (WS_CONT.WS_TC_D_CONT + 1);
                }
            }
            T000_READNEXT_PSTOBLL();
        }
        T000_ENDBR_PSTOBLL();
        CEP.TRC(SCCGWA, WS_AMT.WS_TC_C_AMT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TC_C_CONT);
        CEP.TRC(SCCGWA, WS_AMT.WS_TC_D_AMT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TC_D_CONT);
        T000_STARTBR_TRAN_PSTIBLL();
        T000_READNEXT_PSTIBLL();
        while (WS_READ_TRAN != 'N') {
            if (PSRIBLL.EXG_DC == 'C' 
                && (PSRIBLL.EXG_REC_STS == '0' 
                || PSRIBLL.EXG_REC_STS == '1' 
                || PSRIBLL.EXG_VOUCH_CD.equalsIgnoreCase("98"))) {
                if (PSRIBLL.EXG_REC_STS == '0') {
                    WS_AMT.WS_TH_C_AMT = WS_AMT.WS_TH_C_AMT + PSRIBLL.EXG_AMT;
                    WS_CONT.WS_TH_C_CONT = (short) (WS_CONT.WS_TH_C_CONT + 1);
                }
                if (PSRIBLL.EXG_VOUCH_CD.equalsIgnoreCase("98")) {
                    WS_AMT.WS_TC_C_T_AMT = WS_AMT.WS_TC_C_T_AMT + PSRIBLL.EXG_AMT;
                    WS_CONT.WS_TC_C_T_CONT = (short) (WS_CONT.WS_TC_C_T_CONT + 1);
                }
                if (PSRIBLL.EXG_REC_STS == '1') {
                    WS_AMT.WS_TH_C_T_AMT = WS_AMT.WS_TH_C_T_AMT + PSRIBLL.EXG_AMT;
                    WS_CONT.WS_TH_C_T_CONT = (short) (WS_CONT.WS_TH_C_T_CONT + 1);
                }
            } else {
                if (PSRIBLL.EXG_DC == 'D' 
                    && (PSRIBLL.EXG_REC_STS == '0' 
                    || PSRIBLL.EXG_REC_STS == '1' 
                    || PSRIBLL.EXG_VOUCH_CD.equalsIgnoreCase("98"))) {
                    if (PSRIBLL.EXG_REC_STS == '0') {
                        WS_AMT.WS_TH_D_AMT = WS_AMT.WS_TH_D_AMT + PSRIBLL.EXG_AMT;
                        WS_CONT.WS_TH_D_CONT = (short) (WS_CONT.WS_TH_D_CONT + 1);
                    }
                    if (PSRIBLL.EXG_VOUCH_CD.equalsIgnoreCase("98")) {
                        WS_AMT.WS_TC_D_T_AMT = WS_AMT.WS_TC_D_T_AMT + PSRIBLL.EXG_AMT;
                        WS_CONT.WS_TC_D_T_CONT = (short) (WS_CONT.WS_TC_D_T_CONT + 1);
                    }
                    if (PSRIBLL.EXG_REC_STS == '1') {
                        WS_AMT.WS_TH_D_T_AMT = WS_AMT.WS_TH_D_T_AMT + PSRIBLL.EXG_AMT;
                        WS_CONT.WS_TH_D_T_CONT = (short) (WS_CONT.WS_TH_D_T_CONT + 1);
                    }
                }
            }
            T000_READNEXT_PSTIBLL();
        }
        T000_ENDBR_PSTIBLL();
        CEP.TRC(SCCGWA, WS_AMT.WS_TH_C_AMT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TH_C_CONT);
        CEP.TRC(SCCGWA, WS_AMT.WS_TH_D_AMT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TH_D_CONT);
        CEP.TRC(SCCGWA, WS_AMT.WS_TC_C_AMT);
        CEP.TRC(SCCGWA, WS_AMT.WS_TC_D_AMT);
        WS_AMT.WS_TC_C_ATOTAL = WS_AMT.WS_TC_C_AMT;
        WS_AMT.WS_TC_D_ATOTAL = WS_AMT.WS_TC_D_AMT;
        WS_AMT.WS_TH_C_ATOTAL = WS_AMT.WS_TH_C_T_AMT + WS_AMT.WS_TH_C_AMT + WS_AMT.WS_TC_C_T_AMT;
        WS_AMT.WS_TH_D_ATOTAL = WS_AMT.WS_TH_D_T_AMT + WS_AMT.WS_TH_D_AMT + WS_AMT.WS_TC_D_T_AMT;
        WS_CONT.WS_TC_C_BCONT = (short) (WS_CONT.WS_TC_C_CONT);
        WS_CONT.WS_TC_D_BCONT = (short) (WS_CONT.WS_TC_D_CONT);
        WS_CONT.WS_TH_C_BCONT = (short) (WS_CONT.WS_TH_C_T_CONT + WS_CONT.WS_TH_C_CONT + WS_CONT.WS_TC_C_T_CONT);
        WS_CONT.WS_TH_D_BCONT = (short) (WS_CONT.WS_TH_D_T_CONT + WS_CONT.WS_TH_D_CONT + WS_CONT.WS_TC_D_T_CONT);
    }
    public void B210_CHEK_INPUT() throws IOException,SQLException,Exception {
        if (PSB3110_AWA_3110.EXG_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSB3110_AWA_3110.EXG_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_OUR_OBCR_AMT = WS_AMT.WS_TC_C_ATOTAL;
        WS_FMT.WS_OUR_OBCR_COUNT = WS_CONT.WS_TC_C_BCONT;
        WS_FMT.WS_OUR_OBDR_AMT = WS_AMT.WS_TC_D_ATOTAL;
        WS_FMT.WS_OUR_OBDR_COUNT = WS_CONT.WS_TC_D_BCONT;
        WS_FMT.WS_OUR_IBCR_AMT = WS_AMT.WS_TH_C_ATOTAL;
        WS_FMT.WS_OUR_IBCR_COUNT = WS_CONT.WS_TH_C_BCONT;
        WS_FMT.WS_OUR_IBDR_AMT = WS_AMT.WS_TH_D_ATOTAL;
        WS_FMT.WS_OUR_IBDR_COUNT = WS_CONT.WS_TH_D_BCONT;
        CEP.TRC(SCCGWA, WS_CONT.WS_TC_C_CONT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TC_D_CONT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TH_C_CONT);
        CEP.TRC(SCCGWA, WS_CONT.WS_TH_D_CONT);
        CEP.TRC(SCCGWA, WS_FMT.WS_OUR_OBCR_COUNT);
        CEP.TRC(SCCGWA, WS_FMT.WS_OUR_OBDR_COUNT);
        CEP.TRC(SCCGWA, WS_FMT.WS_OUR_IBCR_COUNT);
        CEP.TRC(SCCGWA, WS_FMT.WS_OUR_IBDR_COUNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 76;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_TRAN_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        IBS.init(SCCGWA, BPCPQORG);
        PSROBLL.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_EXG_AREA_NO = "0" + WS_EXG_AREA_NO;
        PSROBLL.KEY.EXG_AREA_NO = WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        PSROBLL.KEY.EXG_DT = PSB3110_AWA_3110.EXG_DT;
        PSROBLL.KEY.EXG_TMS = PSB3110_AWA_3110.EXG_TMS;
        PSROBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTOBLL_BR.rp = new DBParm();
        PSTOBLL_BR.rp.TableName = "PSTOBLL";
        PSTOBLL_BR.rp.eqWhere = "EXG_BK_NO,EXG_AREA_NO,EXG_DT, EXG_TMS";
        PSTOBLL_BR.rp.where = "EXG_BK_NO = :PSROBLL.KEY.EXG_BK_NO "
            + "AND EXG_AREA_NO = :PSROBLL.KEY.EXG_AREA_NO "
            + "AND EXG_DT = :PSROBLL.KEY.EXG_DT "
            + "AND EXG_TMS = :PSROBLL.KEY.EXG_TMS "
            + "AND EXG_TX_BR = :PSROBLL.EXG_TX_BR";
        PSTOBLL_BR.rp.order = "EXG_BK_NO";
        IBS.STARTBR(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, 0);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
    }
    public void T000_READNEXT_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_ENDBR_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTOBLL_BR);
    }
    public void T000_STARTBR_TRAN_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSRIBLL.KEY.EXG_AREA_NO = WS_EXG_AREA_NO;
        PSRIBLL.KEY.EXG_DT = PSB3110_AWA_3110.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSB3110_AWA_3110.EXG_TMS;
        PSRIBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTIBLL_BR.rp = new DBParm();
        PSTIBLL_BR.rp.TableName = "PSTIBLL";
        PSTIBLL_BR.rp.eqWhere = "EXG_BK_NO,EXG_AREA_NO,EXG_DT, EXG_TMS";
        PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
            + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
            + "AND EXG_DT = :PSRIBLL.KEY.EXG_DT "
            + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS "
            + "AND EXG_TX_BR = :PSRIBLL.EXG_TX_BR";
        PSTIBLL_BR.rp.order = "EXG_BK_NO";
        IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, 0);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
    }
    public void T000_READNEXT_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_ENDBR_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTIBLL_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
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
