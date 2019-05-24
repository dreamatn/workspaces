package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPREAC {
    DBParm BPTPREAC_RD;
    String JIBS_tmp_str[] = new String[10];
    String BPTPREAC = "BPTPREAC";
    char WS_TBL_PREAC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPREAC BPRPREAC = new BPRPREAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCPREAC BPCPREAC;
    public void MP(SCCGWA SCCGWA, BPCPREAC BPCPREAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPREAC = BPCPREAC;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPREAC return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPREAC.RC);
        IBS.init(SCCGWA, BPCPNHIS);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        B20_MAIN_PROCES();
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B20_MAIN_PROCES() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPREAC.FLG);
        if (BPCPREAC.FLG == 'A') {
            B21_ADD_PRE_AC_DATA();
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else if (BPCPREAC.FLG == 'D') {
            B22_DELETE_PRE_AC_DATA();
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else if (BPCPREAC.FLG == 'U') {
            B23_UPDATE_PRE_AC_DATA();
            BPCPNHIS.INFO.TX_TYP = 'M';
        } else if (BPCPREAC.FLG == 'I') {
            B24_INQ_PRE_AC_DATA();
            BPCPNHIS.INFO.TX_TYP = 'O';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = BPCPREAC.REMARK;
        BPCPNHIS.INFO.AC = BPCPREAC.AC;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "BPCPREAC";
        BPCPNHIS.INFO.FMT_ID_LEN = 211;
        S000_CALL_BPZPNHIS();
    }
    public void B21_ADD_PRE_AC_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPREAC);
        BPRPREAC.KEY.AC = BPCPREAC.AC;
        BPRPREAC.STS = BPCPREAC.STS;
        BPRPREAC.CUR_BR = BPCPREAC.CUR_BR;
        BPRPREAC.PRE_BR = BPCPREAC.PRE_BR;
        BPRPREAC.RM_CR_FLG = BPCPREAC.RM_CR_FL;
        BPRPREAC.REMARK = BPCPREAC.REMARK;
        BPRPREAC.CREATE_DT = BPCPREAC.CREATE_DT;
        BPRPREAC.CREATE_TLR = BPCPREAC.CREATE_TLR;
        BPRPREAC.OWNER_BK = BPCPREAC.OWNER_BK;
        BPRPREAC.LAST_UPD_DT = BPCPREAC.LAST_UPD_DT;
        BPRPREAC.LAST_UPD_TLR = BPCPREAC.LAST_UPD_TLR;
        BPRPREAC.REG_TLR = BPCPREAC.LAST_UPD_TLR;
        BPRPREAC.REG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_BPTPREAC();
    }
    public void B22_DELETE_PRE_AC_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPREAC);
        BPRPREAC.KEY.AC = BPCPREAC.AC;
        T000_READ_BPTPREAC_UPD();
        T000_DELETE_BPTPREAC();
    }
    public void B23_UPDATE_PRE_AC_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPREAC);
        BPRPREAC.KEY.AC = BPCPREAC.AC;
        T000_READ_BPTPREAC_UPD();
        BPRPREAC.KEY.AC = BPCPREAC.AC;
        BPRPREAC.CUR_BR = BPCPREAC.CUR_BR;
        BPRPREAC.PRE_BR = BPCPREAC.PRE_BR;
        BPRPREAC.RM_CR_FLG = BPCPREAC.RM_CR_FL;
        BPRPREAC.REMARK = BPCPREAC.REMARK;
        BPRPREAC.LAST_UPD_DT = BPCPREAC.LAST_UPD_DT;
        BPRPREAC.LAST_UPD_TLR = BPCPREAC.LAST_UPD_TLR;
        BPRPREAC.REG_TLR = BPCPREAC.LAST_UPD_TLR;
        BPRPREAC.REG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_BPTPREAC();
    }
    public void B24_INQ_PRE_AC_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPREAC);
        BPRPREAC.KEY.AC = BPCPREAC.AC;
        T000_READ_BPTPREAC();
        BPCPREAC.AC = BPRPREAC.KEY.AC;
        BPCPREAC.STS = BPRPREAC.STS;
        BPCPREAC.CUR_BR = BPRPREAC.CUR_BR;
        BPCPREAC.PRE_BR = BPRPREAC.PRE_BR;
        BPCPREAC.RM_CR_FL = BPRPREAC.RM_CR_FLG;
        BPCPREAC.REMARK = BPRPREAC.REMARK;
        BPCPREAC.LAST_UPD_DT = BPRPREAC.LAST_UPD_DT;
        BPCPREAC.LAST_UPD_TLR = BPRPREAC.LAST_UPD_TLR;
    }
    public void T000_DELETE_BPTPREAC() throws IOException,SQLException,Exception {
        BPTPREAC_RD = new DBParm();
        BPTPREAC_RD.TableName = "BPTPREAC";
        IBS.DELETE(SCCGWA, BPRPREAC, BPTPREAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PREAC_FLAG = '1';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTPREAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_BPTPREAC() throws IOException,SQLException,Exception {
        BPTPREAC_RD = new DBParm();
        BPTPREAC_RD.TableName = "BPTPREAC";
        IBS.WRITE(SCCGWA, BPRPREAC, BPTPREAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PREAC_FLAG = '1';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_PREAC_FLAG = '3';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTPREAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_READ_BPTPREAC() throws IOException,SQLException,Exception {
        BPTPREAC_RD = new DBParm();
        BPTPREAC_RD.TableName = "BPTPREAC";
        IBS.READ(SCCGWA, BPRPREAC, BPTPREAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PREAC_FLAG = '1';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_PREAC_FLAG = '2';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTPREAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_BPTPREAC_UPD() throws IOException,SQLException,Exception {
        BPTPREAC_RD = new DBParm();
        BPTPREAC_RD.TableName = "BPTPREAC";
        BPTPREAC_RD.upd = true;
        IBS.READ(SCCGWA, BPRPREAC, BPTPREAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PREAC_FLAG = '1';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTPREAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_BPTPREAC() throws IOException,SQLException,Exception {
        BPTPREAC_RD = new DBParm();
        BPTPREAC_RD.TableName = "BPTPREAC";
        IBS.REWRITE(SCCGWA, BPRPREAC, BPTPREAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PREAC_FLAG = '1';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTPREAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS ", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
