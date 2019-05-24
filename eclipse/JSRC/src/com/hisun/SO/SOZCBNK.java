package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPOCBR;
import com.hisun.BP.BPRORGS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class SOZCBNK {
    boolean pgmRtn = false;
    Object CWA_PTR;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    BPRORGS BPRORGS = new BPRORGS();
    BPCPOCBR BPCPOCBR = new BPCPOCBR();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZCBNK return!");
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
        IBS.init(SCCGWA, BPCPOCBR);
        BPCPOCBR.INPUT_DATA.FUCN = 'C';
        BPCPOCBR.INPUT_DATA.FLAG = 'A';
        S000_CALL_BPZPOCBR();
        if (pgmRtn) return;
        BPRORGS.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        T000_STARTBR_BPTORGS();
        if (pgmRtn) return;
        T000_READNEXT_BPTORGS();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, BPRORGS.KEY.BNK);
            CEP.TRC(SCCGWA, BPRORGS.KEY.BR);
            CEP.TRC(SCCGWA, BPRORGS.STS);
            if (BPRORGS.STS == 'O') {
                T000_ENDBR_BPTORGS();
                if (pgmRtn) return;
                WS_MSGID = SOCMSG.BAL_NOT;
                S000_ERROR_PROCESS();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTORGS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTORGS();
        if (pgmRtn) return;
        SCRPCWA.BUSS_MODE = 'C';
        T000_REWRITE_SCTCWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOCBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-OPEN-CLOSE-BR", BPCPOCBR);
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
    public void T000_STARTBR_BPTORGS() throws IOException,SQLException,Exception {
        BPTORGS_BR.rp = new DBParm();
        BPTORGS_BR.rp.TableName = "BPTORGS";
        BPTORGS_BR.rp.where = "BNK = :BPRORGS.KEY.BNK";
        IBS.STARTBR(SCCGWA, BPRORGS, this, BPTORGS_BR);
    }
    public void T000_READNEXT_BPTORGS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGS, this, BPTORGS_BR);
    }
    public void T000_ENDBR_BPTORGS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGS_BR);
    }
    public void T000_READ_UPD_SCTCWA() throws IOException,SQLException,Exception {
        SCRCWAT.KEY.BANK_NO = "043";
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
