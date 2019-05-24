package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTCIBB {
    DBParm BPTTLVB_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_BPZTCIBB = "BPZTCIBB";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    char WS_TBL_LIB_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLVB BPRTLVB = new BPRTLVB();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPCLIB BPCPCLIB = new BPCPCLIB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    int WS_TEMP_CNT = 0;
    SCCGWA SCCGWA;
    BPCTCIBB BPCTCIBB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCTCIBB BPCTCIBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCIBB = BPCTCIBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTCIBB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPCLIB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTCIBB.INFO.VB_FLG == 3
            || BPCTCIBB.INFO.VB_FLG == 4) {
            B010_GROUP_RECORDS_PROC();
            if (pgmRtn) return;
        } else if (BPCTCIBB.INFO.VB_FLG == 1
            || BPCTCIBB.INFO.VB_FLG == 2) {
            B020_GROUP_RECORDS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPCLIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_GROUP_RECORDS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.LAST_TLR = BPCTCIBB.INFO.TLR;
        BPRTLVB.PLBOX_TP = BPCTCIBB.INFO.VB_FLG;
        T000_GROUP_BPTCLIB();
        if (pgmRtn) return;
        BPCTCIBB.RETURN_CNT = WS_TEMP_CNT;
    }
    public void B020_GROUP_RECORDS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCTCIBB.INFO.MGR_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        BPRTLVB.KEY.BR = BPCFTLRQ.INFO.TLR_BR;
        BPRTLVB.LAST_TLR = BPCTCIBB.INFO.MGR_TLR;
        BPRTLVB.PLBOX_TP = BPCTCIBB.INFO.VB_FLG;
        T000_GROUP_BPTCLIB1();
        if (pgmRtn) return;
        BPCTCIBB.RETURN_CNT = WS_TEMP_CNT;
    }
    public void T000_GROUP_BPTCLIB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.set = "WS-TEMP-CNT=COUNT(*)";
        BPTTLVB_RD.where = "LAST_TLR = :BPRTLVB.LAST_TLR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP";
        IBS.GROUP(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
    }
    public void T000_GROUP_BPTCLIB1() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.set = "WS-TEMP-CNT=COUNT(*)";
        BPTTLVB_RD.where = "LAST_TLR = :BPRTLVB.LAST_TLR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND BR = :BPRTLVB.KEY.BR";
        IBS.GROUP(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCTCIBB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTCIBB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTCIBB = ");
            CEP.TRC(SCCGWA, BPCTCIBB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
