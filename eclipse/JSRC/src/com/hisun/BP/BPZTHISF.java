package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTHISF {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTHIS_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTHISF";
    String K_TBL_THIS = "BPTTHIS ";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    char WS_TBL_THIS_FLAG = ' ';
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPCASH BPRPCASH = new BPRPCASH();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRTHIS BPRTHIS = new BPRTHIS();
    SCCGWA SCCGWA;
    BPRTHIS BPRTHIS1;
    BPCTHISF BPCTHISF;
    public void MP(SCCGWA SCCGWA, BPCTHISF BPCTHISF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTHISF = BPCTHISF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTHISF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTHIS1 = (BPRTHIS) BPCTHISF.POINTER;
        IBS.init(SCCGWA, BPRTHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTHIS1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTHISF.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTHISF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTHISF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTHISF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTHISF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTHIS, BPRTHIS1);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTHIS.TLR.trim().length() == 0) {
            BPRTHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CEP.TRC(SCCGWA, "NCB041902");
        CEP.TRC(SCCGWA, BPRTHIS.CASH_NO);
        if (BPRTHIS.CASH_NO.trim().length() == 0) {
            IBS.init(SCCGWA, BPRPCASH);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPCASH.KEY.TYP = "CASH ";
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRPCASH.KEY.CD = SCCGWA.COMM_AREA.SERV_CODE;
            BPCPRMM.FUNC = '3';
            BPCPRMM.DAT_PTR = BPRPCASH;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            } else {
                BPRTHIS.CASH_NO = BPRPCASH.DATA_TXT.CASH_NO;
                if (BPRTHIS.TR_CODE.trim().length() == 0) {
                    BPRTHIS.TR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
                }
            }
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        }
        CEP.TRC(SCCGWA, "NCB041901");
        CEP.TRC(SCCGWA, BPRTHIS.CASH_NO);
        CEP.TRC(SCCGWA, BPRTHIS.TR_CODE);
        T000_WRITE_BPTTHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTHIS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_THIS_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_THIS_NOTFND, BPCTHISF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTHIS.TLR.trim().length() == 0) {
            BPRTHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        T000_REWRITE_BPTTHIS();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTHIS();
        if (pgmRtn) return;
        if (WS_TBL_THIS_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_THIS_NOTFND, BPCTHISF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTHIS() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        IBS.READ(SCCGWA, BPRTHIS, BPTTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHISF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHISF.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTHIS() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTHIS, BPTTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHISF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTHISF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTHIS_UPD() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRTHIS, BPTTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHISF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHISF.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTHIS() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        IBS.REWRITE(SCCGWA, BPRTHIS, BPTTHIS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTHISF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTHISF = ");
            CEP.TRC(SCCGWA, BPCTHISF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
