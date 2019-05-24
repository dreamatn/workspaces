package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPOCBR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class SOZOBNK {
    boolean pgmRtn = false;
    Object CWA_PTR;
    String WS_END_PROC = "SCPWAT94";
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    BPCPOCBR BPCPOCBR = new BPCPOCBR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZOBNK return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_UPD_SCTCWA();
        if (pgmRtn) return;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SCRPCWA.SYS_STUS != 'A') {
            WS_MSGID = SOCMSG.SYS_CLS;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        if (SCRPCWA.BUSS_MODE == 'O') {
            WS_MSGID = SOCMSG.SO_ERR_BUSS_OPEN;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPOCBR);
        BPCPOCBR.INPUT_DATA.FUCN = 'O';
        BPCPOCBR.INPUT_DATA.FLAG = 'A';
        S000_CALL_BPZPOCBR();
        if (pgmRtn) return;
        SCRPCWA.BUSS_MODE = 'O';
        T000_REWRITE_SCTCWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOCBR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "before");
        IBS.CALLCPN(SCCGWA, "BP-P-OPEN-CLOSE-BR", BPCPOCBR);
        CEP.TRC(SCCGWA, BPCPOCBR.RC.RC_CODE);
        if (BPCPOCBR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPOCBR.RC);
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_SEND_MSG_TO_OP() throws IOException,SQLException,Exception {
    }
    public void T000_READ_UPD_SCTCWA() throws IOException,SQLException,Exception {
        SCRCWAT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        SCTCWA_RD.upd = true;
        IBS.READ(SCCGWA, SCRCWAT, SCTCWA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_MSGID = SOCMSG.SO_SYS_ERROR;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRCWAT);
