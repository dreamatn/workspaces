package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNZSWLAD {
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCSWLAD LNCSWLCR = new LNCSWLAD();
    LNRFWDH LNRFWDH = new LNRFWDH();
    LNCSWLAD LNCSWLDD = new LNCSWLAD();
    SCCGWA SCCGWA;
    LNCSWLAD LNCSWLAD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, LNCSWLAD LNCSWLAD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSWLAD = LNCSWLAD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSWLAD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSWLAD.RC.RC_APP = "LN";
        LNCSWLAD.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && !JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CAN_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CREATE_LOAN_PROC();
        if (pgmRtn) return;
        B300_LOAN_DRAWDOWN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CREATE_LOAN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.FLT_MTH);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.VRTU_FLG);
        IBS.init(SCCGWA, LNCSWLCR);
        IBS.CLONE(SCCGWA, LNCSWLAD, LNCSWLCR);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.FLT_MTH);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.BOOK_BR);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.BOOK_BR);
        S000_CALL_LNZSWLCR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, LNCSWLCR, LNCSWLAD);
    }
    public void B200_FORWD_DRAWDOWN_PROC() throws IOException,SQLException,Exception {
        B210_CREATE_FWDH_PROC();
        if (pgmRtn) return;
    }
    public void B210_CREATE_FWDH_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_LNTFWDH();
        if (pgmRtn) return;
    }
    public void B300_LOAN_DRAWDOWN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWLDD);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.CTA_NO);
        IBS.CLONE(SCCGWA, LNCSWLAD, LNCSWLDD);
        CEP.TRC(SCCGWA, LNCSWLDD.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.DRW_AMT);
        S000_CALL_LNZSWLDD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, LNCSWLDD, LNCSWLAD);
    }
    public void T000_WRITE_LNTFWDH() throws IOException,SQLException,Exception {
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        IBS.WRITE(SCCGWA, LNRFWDH, LNTFWDH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTFWDH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSWLCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-WLLOAN-CREAT", LNCSWLCR);
        if (LNCSWLCR.RC.RC_RTNCODE != 0) {
