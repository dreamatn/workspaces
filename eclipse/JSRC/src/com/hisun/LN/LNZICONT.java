package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICONT {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    DBParm LNTLOAN_RD;
    brParm LNTCONT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CLDD = "CLD";
    LNZICONT_WS_FLGS WS_FLGS = new LNZICONT_WS_FLGS();
    String WS_ERR_MSG = " ";
    int WS_COMP_DATE = 0;
    int WS_LOAN_DATE = 0;
    int WS_UNDUE_LOAN_DATE = 0;
    short WS_UNDUE_DATE = 0;
    short WS_IDX = 0;
    String WS_CONTRACT_TYPE = " ";
    String WS_CHK_POINTER;
    LNZICONT_REDEFINES22 REDEFINES22 = new LNZICONT_REDEFINES22();
    LNZICONT_REDEFINES24 REDEFINES24 = new LNZICONT_REDEFINES24();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRCONT LNRCONT = new LNRCONT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    SCCGWA SCCGWA;
    LNCICONT LNCICONT;
    String LK_CONT = " ";
    String LK_LOAN = " ";
    String LK_ICTL = " ";
    public void MP(SCCGWA SCCGWA, LNCICONT LNCICONT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICONT = LNCICONT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICONT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICONT.RC.RC_MMO = "LN";
        LNCICONT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICONT.FUNC);
        if (LNCICONT.FUNC == 'A'
            || LNCICONT.FUNC == 'B') {
            B010_QUERY_CONT();
            if (pgmRtn) return;
        } else if (LNCICONT.FUNC == 'C') {
            LNRCONT.FATHER_CONTRACT = LNCICONT.FATHER_CTA_REQ;
            LNRCONT.KEY.CONTRACT_NO = LNCICONT.CTA_NO_FROM;
            T000_STARTBR_LNTCONT_TRAN();
            if (pgmRtn) return;
            R000_BROWSE_CONT();
            if (pgmRtn) return;
        } else if (LNCICONT.FUNC == 'D'
            || LNCICONT.FUNC == 'E') {
            B050_QUERY_CI();
            if (pgmRtn) return;
        } else if (LNCICONT.FUNC == 'F') {
            B060_QUERY_MAX_MAT_DT();
            if (pgmRtn) return;
        } else if (LNCICONT.FUNC == 'G') {
            B070_QUERY_PAY_PAL_DT();
            if (pgmRtn) return;
        } else if (LNCICONT.FUNC == 'H') {
            B080_QUERY_PAY_INT_DT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCICONT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCICONT.FUNC == 'A' 
            || LNCICONT.FUNC == 'B') {
            if (LNCICONT.CONTRACT_NO.trim().length() == 0) {
                LNCICONT.RC.RC_MMO = "LN";
                LNCICONT.RC.RC_CODE = 9001;
            }
            WS_CHK_POINTER = LNCICONT.CONT_POINTER;
            if (WS_CHK_POINTER == null 
                || REDEFINES22.WS_CHK_POINTER_NUM == 0 
                || REDEFINES24.WS_CHK_POINTER_CHR == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.POINTER_NULL, LNCICONT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                LK_CONT = IBS.CLS2CPY(SCCGWA, LNCICONT.CONT_POINTER);
            }
            WS_CHK_POINTER = LNCICONT.LOAN_POINTER;
            if (WS_CHK_POINTER == null 
                || REDEFINES22.WS_CHK_POINTER_NUM == 0 
                || REDEFINES24.WS_CHK_POINTER_CHR == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.POINTER_NULL, LNCICONT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                LK_LOAN = IBS.CLS2CPY(SCCGWA, LNCICONT.LOAN_POINTER);
            }
            WS_CHK_POINTER = LNCICONT.ICTL_POINTER;
            if (WS_CHK_POINTER == null 
                || REDEFINES22.WS_CHK_POINTER_NUM == 0 
                || REDEFINES24.WS_CHK_POINTER_CHR == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.POINTER_NULL, LNCICONT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                LK_ICTL = IBS.CLS2CPY(SCCGWA, LNCICONT.ICTL_POINTER);
            }
        }
    }
    public void B010_QUERY_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCICONT.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNCICONT.CONTRACT_NO);
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        if (WS_FLGS.WS_READ_LNTCONT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.CONT_NFND, LNCICONT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRCONT);
            LK_CONT = JIBS_tmp_str[0];
        }
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD)) {
            IBS.init(SCCGWA, LNRLOAN);
            LNRLOAN.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
            T000_READ_LNTLOAN();
            if (pgmRtn) return;
            if (WS_FLGS.WS_READ_LNTLOAN_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LOAN_NFND, LNCICONT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRLOAN);
                LK_LOAN = JIBS_tmp_str[0];
            }
            IBS.init(SCCGWA, LNRICTL);
            LNRICTL.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            T000_READ_LNTICTL();
            if (pgmRtn) return;
            if (WS_FLGS.WS_READ_LNTICTL_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ICTL_NFND, LNCICONT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRICTL);
                LK_ICTL = JIBS_tmp_str[0];
            }
            IBS.init(SCCGWA, LNCSLNQ);
            LNCSLNQ.COMM_DATA.LN_AC = LNRCONT.KEY.CONTRACT_NO;
            LNCSLNQ.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCSLNQ.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.SUF_NO = "0" + LNCSLNQ.COMM_DATA.SUF_NO;
            S000_CALL_LNZSLNQ();
            if (pgmRtn) return;
            LNCICONT.AMT_AREA.PRINCIPAL = LNCSLNQ.COMM_DATA.PRIN;
            LNCICONT.AMT_AREA.OS_BAL = LNCSLNQ.COMM_DATA.TOT_BAL;
            LNCICONT.AMT_AREA.PRIN_EQU = LNCSLNQ.COMM_DATA.PRIN_EQU;
            LNCICONT.AMT_AREA.INT_REC = LNCSLNQ.COMM_DATA.DUEINT;
            LNCICONT.AMT_AREA.INT_PAID = LNCSLNQ.COMM_DATA.RCVINT;
            LNCICONT.AMT_AREA.LC_REC = LNCSLNQ.COMM_DATA.DUELC;
            LNCICONT.AMT_AREA.LC_PAID = LNCSLNQ.COMM_DATA.RCVLC;
        }
    }
    public void B050_QUERY_CI() throws IOException,SQLException,Exception {
    }
    public void B060_QUERY_MAX_MAT_DT() throws IOException,SQLException,Exception {
    }
    public void B070_QUERY_PAY_PAL_DT() throws IOException,SQLException,Exception {
    }
    public void B080_QUERY_PAY_INT_DT() throws IOException,SQLException,Exception {
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READ_LNTLOAN_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READ_LNTLOAN_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTLOAN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTCONT_TRAN() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT "
            + "AND CONTRACT_NO >= :LNRCONT.KEY.CONTRACT_NO";
        LNTCONT_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_LNTCONT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FLGS.WS_READNEXT_LNTCONT_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FLGS.WS_READNEXT_LNTCONT_FLG = 'E';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTCONT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_BROWSE_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICONT.GROUP_DATA);
        WS_FLGS.WS_READNEXT_LNTCONT_FLG = 'N';
        WS_IDX = 0;
        T000_READNEXT_LNTCONT();
        if (pgmRtn) return;
        while (WS_FLGS.WS_READNEXT_LNTCONT_FLG != 'E' 
            && WS_IDX <= 11) {
            WS_IDX += 1;
            CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
            if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD)) {
                LNRLOAN.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
                T000_READ_LNTLOAN();
                if (pgmRtn) return;
                if (WS_FLGS.WS_READ_LNTLOAN_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LOAN_NFND, LNCICONT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            LNCICONT.GROUP_DATA.GROUP[WS_IDX-1].CTA_NO = LNRCONT.KEY.CONTRACT_NO;
            LNCICONT.GROUP_DATA.GROUP[WS_IDX-1].CONTRACT_TYPE = LNRCONT.CONTRACT_TYPE;
            LNCICONT.GROUP_DATA.GROUP[WS_IDX-1].STATUS = LNRCONT.CTA_STS;
            LNCICONT.GROUP_DATA.GROUP[WS_IDX-1].PROD_CD = LNRCONT.PROD_CD;
            LNCICONT.GROUP_DATA.GROUP[WS_IDX-1].ORIG_PRIN = LNRCONT.ORIG_TOT_AMT;
            LNCICONT.GROUP_DATA.GROUP[WS_IDX-1].FATHER_CONTRACT = LNRCONT.FATHER_CONTRACT;
            T000_READNEXT_LNTCONT();
            if (pgmRtn) return;
        }
        if (WS_FLGS.WS_READNEXT_LNTCONT_FLG == 'E') {
            LNCICONT.FLAG = 'Y';
        }
        LNCICONT.CTA_NO_FROM = LNRCONT.KEY.CONTRACT_NO;
        T000_ENDBR_LNTCONT();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            LNCICONT.RC.RC_MMO = LNCSLNQ.RC.RC_APP;
            LNCICONT.RC.RC_CODE = LNCSLNQ.RC.RC_RTNCODE;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICONT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCICONT=");
            CEP.TRC(SCCGWA, LNCICONT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
