package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRSSAF {
    DBParm BPTFSAF_RD;
    boolean pgmRtn = false;
    String K_TBL_FSAF = "BPTFSAF";
    String K_HIS_COPYBOOK = "BPRFAGOR";
    String K_HIS_REMARK = "TXN INFOMATION MAINTAIN";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFSAF BPRFSAF = new BPRFSAF();
    BPRFSAF BPROSAF = new BPRFSAF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCRSAF1 BPCRSAF1;
    BPRFSAF BPRFSAL;
    public void MP(SCCGWA SCCGWA, BPCRSAF1 BPCRSAF1) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSAF1 = BPCRSAF1;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRSSAF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFSAL = (BPRFSAF) BPCRSAF1.INFO.POINTER;
        CEP.TRC(SCCGWA, BPRFSAF);
        IBS.init(SCCGWA, BPRFSAF);
        IBS.CLONE(SCCGWA, BPRFSAL, BPRFSAF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSAF1.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
            B100_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCRSAF1.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSAF1.INFO.FUNC == 'M') {
            B030_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
            B100_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCRSAF1.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSAF1.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFSAF, BPRFSAL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFSAF();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPTFSAF();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.CLONE(SCCGWA, BPRFSAF, BPROSAF);
        T000_REWRITE_BPTFSAF();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFSAF();
        if (pgmRtn) return;
    }
    public void B100_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCRSAF1.INFO.FUNC == 'C') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFSAF;
        }
        if (BPCRSAF1.INFO.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROSAF;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFSAF;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFSAF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSAF);
        BPTFSAF_RD = new DBParm();
        BPTFSAF_RD.TableName = "BPTFSAF";
        IBS.READ(SCCGWA, BPRFSAF, BPTFSAF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSAF1.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSAF1.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READUPD_BPTFSAF() throws IOException,SQLException,Exception {
        BPTFSAF_RD = new DBParm();
        BPTFSAF_RD.TableName = "BPTFSAF";
        BPTFSAF_RD.upd = true;
        IBS.READ(SCCGWA, BPRFSAF, BPTFSAF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSAF1.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSAF1.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFSAF() throws IOException,SQLException,Exception {
        BPTFSAF_RD = new DBParm();
        BPTFSAF_RD.TableName = "BPTFSAF";
        IBS.REWRITE(SCCGWA, BPRFSAF, BPTFSAF_RD);
    }
    public void T000_WRITE_BPTFSAF() throws IOException,SQLException,Exception {
        BPTFSAF_RD = new DBParm();
        BPTFSAF_RD.TableName = "BPTFSAF";
        BPTFSAF_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFSAF, BPTFSAF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSAF1.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRSAF1.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FSAF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSAF1.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSAF1 = ");
            CEP.TRC(SCCGWA, BPCRSAF1);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
