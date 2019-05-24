package com.hisun.SO;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SOZSSEQ {
    DBParm SOTJRN_RD;
    DBParm SOTSEQ_RD;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SORSEQ SORSEQ = new SORSEQ();
    int WORK_JRN_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SOZSSEQ return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        T000_GROUP_JOURNAL();
        S000_RESET_SEQ();
    }
    public void T000_GROUP_JOURNAL() throws IOException,SQLException,Exception {
        SOTJRN_RD = new DBParm();
        SOTJRN_RD.TableName = "SOTJRN";
        SOTJRN_RD.set = "WORK-JRN-NO=IFNULL(MAX(JRN_NO),0)";
        IBS.GROUP(SCCGWA, SORJRN, this, SOTJRN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTJRN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_RESET_SEQ() throws IOException,SQLException,Exception {
        WORK_JRN_NO = WORK_JRN_NO + 1000;
        SCCGWA.COMM_AREA.JRN_NO = WORK_JRN_NO;
        SORSEQ.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SOTSEQ_RD = new DBParm();
        SOTSEQ_RD.TableName = "SOTSEQ";
        SOTSEQ_RD.upd = true;
        IBS.READ(SCCGWA, SORSEQ, SOTSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTSEQ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
        WORK_JRN_NO = WORK_JRN_NO + 1;
        SORSEQ.JRN_NO = WORK_JRN_NO;
        SOTSEQ_RD = new DBParm();
        SOTSEQ_RD.TableName = "SOTSEQ";
        IBS.REWRITE(SCCGWA, SORSEQ, SOTSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTSEQ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
