package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZSPMN {
    DBParm SCTCWA_RD;
    String JIBS_tmp_str[] = new String[10];
    short WS_I = 0;
    short WS_J = 0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCRCWAT SCRCWAT = new SCRCWAT();
    SCCFMT SCCFMT = new SCCFMT();
    short WS_SERV_BANK_NO = 0;
    String WS_SERV_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCRCWA SCRCWA = new SCRCWA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZSPMN return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        C00_READUPD_CWA();
        C00_REWRITE_CWA();
    }
    public void C00_READUPD_CWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRCWA);
        SCRCWA.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        SCTCWA_RD.upd = true;
        SCTCWA_RD.errhdl = true;
        IBS.READ(SCCGWA, SCRCWA, SCTCWA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_CWA() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRCWA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRCWAT);
        SCRCWAT.FLOW_AREA[80-1].FLOW_IND = '1';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRCWAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRCWA);
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        SCTCWA_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SCRCWA, SCTCWA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
