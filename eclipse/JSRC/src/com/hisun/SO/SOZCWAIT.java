package com.hisun.SO;

import com.hisun.BP.BPRTRT;
import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRJSCH;

import java.io.IOException;
import java.sql.SQLException;

public class SOZCWAIT {
    int JIBS_tmp_int;
    DBParm SOTWAIT_RD;
    DBParm SCTJSCH_RD;
    boolean pgmRtn = false;
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    short WK_I = 0;
    String[] WK_WAIT = new String[8];
    String WK_PRE_PROC1 = " ";
    String WK_PRE_PROC2 = " ";
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SORWAIT SORWAIT = new SORWAIT();
    SCRJSCH SCRJSCH = new SCRJSCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTRT SORSERV;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        E00_GO_BACK();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORSERV = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        C01_CHECK_SUBMIT();
        if (pgmRtn) return;
        C05_CHECK_WAIT();
        if (pgmRtn) return;
    }
    public void C01_CHECK_SUBMIT() throws IOException,SQLException,Exception {
        SORWAIT.KEY.ID = SCCGWA.COMM_AREA.SERV_CODE;
        T01_READ_UPD_WAIT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.POST_DATE == SORWAIT.PROC_DATE) {
            CEP.ERR(SCCGWA, SOCMSG.SO_STEP_DONE);
        }
        SORWAIT.PROC_DATE = SCCGWA.COMM_AREA.POST_DATE;
        if (SORSERV.CTRL_WORD == null) SORSERV.CTRL_WORD = "";
        JIBS_tmp_int = SORSERV.CTRL_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) SORSERV.CTRL_WORD += " ";
        if (SORSERV.CTRL_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
            SORWAIT.END_DATE = SCCGWA.COMM_AREA.POST_DATE;
        }
        T05_REWRITE_WAIT();
        if (pgmRtn) return;
        WK_WAIT[1-1] = SORWAIT.SERV1;
        WK_WAIT[2-1] = SORWAIT.SERV2;
        WK_WAIT[3-1] = SORWAIT.SERV3;
        WK_WAIT[4-1] = SORWAIT.SERV4;
        WK_WAIT[5-1] = SORWAIT.SERV5;
        WK_WAIT[6-1] = SORWAIT.SERV6;
        WK_WAIT[7-1] = SORWAIT.SERV7;
        WK_WAIT[8-1] = SORWAIT.SERV8;
        WK_PRE_PROC1 = SORWAIT.PRE_PROC1;
        WK_PRE_PROC2 = SORWAIT.PRE_PROC2;
    }
    public void C05_CHECK_WAIT() throws IOException,SQLException,Exception {
        for (WK_I = 1; WK_I <= 8 
            && WK_WAIT[WK_I-1].trim().length() != 0; WK_I += 1) {
            SORWAIT.KEY.ID = WK_WAIT[WK_I-1];
            T10_READ_ONLY_WAIT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SORWAIT.KEY.ID);
            CEP.TRC(SCCGWA, SORWAIT.END_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.POST_DATE);
            if (SORWAIT.END_DATE != SCCGWA.COMM_AREA.POST_DATE) {
                CEP.ERR(SCCGWA, SOCMSG.SO_WAIT_STEP);
            }
        }
        if (WK_PRE_PROC1.trim().length() > 0) {
            SCRJSCH.KEY.JOB_NAME = WK_PRE_PROC1;
            T15_READ_ONLY_BAT_SCH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCRJSCH.JOB_DATE);
            CEP.TRC(SCCGWA, SCRCWA.BAT_END_DATE);
            if (SCRJSCH.JOB_DATE != SCRCWA.BAT_END_DATE) {
                CEP.ERR(SCCGWA, SOCMSG.SO_ERR_WAIT_JOB);
            }
        }
        if (WK_PRE_PROC2.trim().length() > 0) {
            SCRJSCH.KEY.JOB_NAME = WK_PRE_PROC2;
            T15_READ_ONLY_BAT_SCH();
            if (pgmRtn) return;
            if (SCRJSCH.JOB_DATE != SCRCWA.BAT_END_DATE) {
                CEP.ERR(SCCGWA, SOCMSG.SO_ERR_WAIT_JOB);
            }
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
    }
    public void E00_GO_BACK() throws IOException,SQLException,Exception {
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void T01_READ_UPD_WAIT() throws IOException,SQLException,Exception {
        SORWAIT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        SOTWAIT_RD.upd = true;
        IBS.READ(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            pgmRtn = true;
            return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTWAIT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T05_REWRITE_WAIT() throws IOException,SQLException,Exception {
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        IBS.REWRITE(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTWAIT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T10_READ_ONLY_WAIT() throws IOException,SQLException,Exception {
        SORWAIT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        IBS.READ(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTWAIT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T15_READ_ONLY_BAT_SCH() throws IOException,SQLException,Exception {
        SCTJSCH_RD = new DBParm();
        SCTJSCH_RD.TableName = "SCTJSCH";
        IBS.READ(SCCGWA, SCRJSCH, SCTJSCH_RD);
        CEP.TRC(SCCGWA, SCRJSCH.KEY.JOB_NAME);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTJSCH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
