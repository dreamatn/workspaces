package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZFTLT {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTLT_RD;
    boolean pgmRtn = false;
    String WK_TEMP_CODE = " ";
    short WK_CNT = 0;
    int RESP_CODE = 0;
    SOZFTLT_WS_MSGID WS_MSGID = new SOZFTLT_WS_MSGID();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    String WK_TLT_ID = " ";
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZFTLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_TLT_ID = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.KEY.TLR = WK_TLT_ID;
        T000_READ_UPD_BPTTLT();
        if (pgmRtn) return;
        if (BPRTLT.SIGN_STS != 'O') {
            IBS.CPY2CLS(SCCGWA, SOCMSG.SO_TLR_SIGN_OFF, WS_MSGID);
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        BPRTLT.SIGN_STS = 'T';
        BPRTLT.SIGN_TIMES = SCCGWA.COMM_AREA.TR_TIME;
        T000_REWRITE_BPTTLT();
        if (pgmRtn) return;
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
    }
    public void T000_READ_UPD_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, SOCMSG.SO_TLR_NOT_FOUND, WS_MSGID);
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_REWRITE_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.REWRITE(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
