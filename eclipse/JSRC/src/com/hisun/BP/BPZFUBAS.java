package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZFUBAS {
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "FEE BAS INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "BPRFBAS";
    String WS_ERR_MSG = " ";
    short WS_FEE_NO = 0;
    int WS_GLMST1 = 0;
    int WS_GLMST2 = 0;
    char WS_TBL_FBAS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPRFBAS BPROBAS = new BPRFBAS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCOFBAS BPCOUBAS;
    public void MP(SCCGWA SCCGWA, BPCOFBAS BPCOUBAS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUBAS = BPCOUBAS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUBAS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPROBAS);
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCUCNGM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOUBAS.FUNC);
        if (BPCOUBAS.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUBAS.FUNC == 'A') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B020_CREATE_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B020_CREATE_PROCESS();
                if (pgmRtn) return;
            }
        } else if (BPCOUBAS.FUNC == 'U') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B030_MODIFY_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B030_MODIFY_PROCESS();
                if (pgmRtn) return;
            }
        } else if (BPCOUBAS.FUNC == 'D') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B040_DELETE_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B040_DELETE_PROCESS();
                if (pgmRtn) return;
            }
        } else if (BPCOUBAS.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOUBAS.FUNC != 'B') {
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        BPRFBAS.KEY.FEE_CODE = BPCOUBAS.KEY.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        WS_GLMST1 = BPRFBAS.GL_MASTER1;
        WS_GLMST2 = BPRFBAS.GL_MASTER2;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCTFBAS.INFO.FUNC = 'C';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUCNGM);
        WS_GLMST1 = 0;
        WS_GLMST2 = 0;
        BPCUCNGM.FUNC = 'A';
        BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
        if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
        JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
        BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "0" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
        BPCUCNGM.KEY.CNTR_TYPE = "FEES";
        BPCUCNGM.DATA[1-1].GLMST = BPCOUBAS.VAL.GL_MASTERNO1;
        WS_GLMST1 = BPCOUBAS.VAL.GL_MASTERNO1;
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        if (BPCOUBAS.VAL.GL_MASTERNO2 != 0) {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.FUNC = 'A';
            BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
            if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
            JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
            BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "1" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
            BPCUCNGM.KEY.CNTR_TYPE = "FEES";
            BPCUCNGM.DATA[1-1].GLMST = BPCOUBAS.VAL.GL_MASTERNO2;
            WS_GLMST2 = BPCOUBAS.VAL.GL_MASTERNO2;
            S000_CALL_BPZUCNGM();
            if (pgmRtn) return;
        }
        T000_WRITE_HITORY();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS_CN() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCTFBAS.INFO.FUNC = 'C';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        T000_WRITE_HITORY();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B031_QUERY_RECORDE_FOR_RUPDATE();
        if (pgmRtn) return;
        T030_01_COMPARE_DATA();
        if (pgmRtn) return;
        B032_UPDATE_RECORDE();
        if (pgmRtn) return;
        T000_WRITE_HITORY();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS_CN() throws IOException,SQLException,Exception {
        B031_QUERY_RECORDE_FOR_RUPDATE();
        if (pgmRtn) return;
        T030_01_COMPARE_DATA();
        if (pgmRtn) return;
        B032_UPDATE_RECORDE_CN();
        if (pgmRtn) return;
        T000_WRITE_HITORY();
        if (pgmRtn) return;
    }
    public void B031_QUERY_RECORDE_FOR_RUPDATE() throws IOException,SQLException,Exception {
        BPCTFBAS.INFO.FUNC = 'R';
        BPRFBAS.KEY.FEE_CODE = BPCOUBAS.KEY.FEE_CODE;
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRFBAS, BPROBAS);
        WS_GLMST1 = BPRFBAS.GL_MASTER1;
        WS_GLMST2 = BPRFBAS.GL_MASTER2;
    }
    public void B032_UPDATE_RECORDE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPCUCNGM);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCTFBAS.INFO.FUNC = 'U';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.FUNC = 'Q';
        BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
        if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
        JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
        BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "0" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
        BPCUCNGM.KEY.CNTR_TYPE = "FEES";
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.FUNC = 'A';
        } else {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.FUNC = 'U';
        }
        BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
        if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
        JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
        BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "0" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
        BPCUCNGM.KEY.CNTR_TYPE = "FEES";
        BPCUCNGM.DATA[1-1].GLMST = BPCOUBAS.VAL.GL_MASTERNO1;
        WS_GLMST1 = BPCOUBAS.VAL.GL_MASTERNO1;
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_GLMST2);
        CEP.TRC(SCCGWA, BPCOUBAS.VAL.GL_MASTERNO2);
        if (WS_GLMST2 != BPCOUBAS.VAL.GL_MASTERNO2) {
            if (WS_GLMST2 != 0) {
                IBS.init(SCCGWA, BPCUCNGM);
                BPCUCNGM.FUNC = 'D';
                BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
                if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
                JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
                BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "1" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
                BPCUCNGM.KEY.CNTR_TYPE = "FEES";
                S000_CALL_BPZUCNGM();
                if (pgmRtn) return;
            }
            if (BPCOUBAS.VAL.GL_MASTERNO2 != 0) {
                IBS.init(SCCGWA, BPCUCNGM);
                BPCUCNGM.FUNC = 'A';
                BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
                if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
                JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
                BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "1" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
                BPCUCNGM.KEY.CNTR_TYPE = "FEES";
                BPCUCNGM.DATA[1-1].GLMST = BPCOUBAS.VAL.GL_MASTERNO2;
                S000_CALL_BPZUCNGM();
                if (pgmRtn) return;
            }
            WS_GLMST2 = BPCOUBAS.VAL.GL_MASTERNO2;
        }
    }
    public void B032_UPDATE_RECORDE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPCUCNGM);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCTFBAS.INFO.FUNC = 'U';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCTFBAS.INFO.FUNC = 'R';
        BPRFBAS.KEY.FEE_CODE = BPCOUBAS.KEY.FEE_CODE;
        BPRFBAS.FEE_NO = BPCOUBAS.VAL.FEE_NO;
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPCUCNGM);
        BPCTFBAS.INFO.FUNC = 'D';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUCNGM);
        WS_GLMST1 = 0;
        WS_GLMST2 = 0;
        BPCUCNGM.FUNC = 'D';
        BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
        if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
        JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
        BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "0" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
        BPCUCNGM.KEY.CNTR_TYPE = "FEES";
        WS_GLMST1 = BPCOUBAS.VAL.GL_MASTERNO1;
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        if (BPCOUBAS.VAL.GL_MASTERNO2 != 0) {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.FUNC = 'D';
            BPCUCNGM.KEY.AC = BPCOUBAS.KEY.FEE_CODE;
            if (BPCUCNGM.KEY.AC == null) BPCUCNGM.KEY.AC = "";
            JIBS_tmp_int = BPCUCNGM.KEY.AC.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCUCNGM.KEY.AC += " ";
            BPCUCNGM.KEY.AC = BPCUCNGM.KEY.AC.substring(0, 6 - 1) + "1" + BPCUCNGM.KEY.AC.substring(6 + 1 - 1);
            BPCUCNGM.KEY.CNTR_TYPE = "FEES";
            WS_GLMST2 = BPCOUBAS.VAL.GL_MASTERNO2;
            S000_CALL_BPZUCNGM();
            if (pgmRtn) return;
        }
        T000_WRITE_HITORY();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS_CN() throws IOException,SQLException,Exception {
        BPCTFBAS.INFO.FUNC = 'R';
        BPRFBAS.KEY.FEE_CODE = BPCOUBAS.KEY.FEE_CODE;
        BPRFBAS.FEE_NO = BPCOUBAS.VAL.FEE_NO;
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTFBAS);
        IBS.init(SCCGWA, BPCUCNGM);
        BPCTFBAS.INFO.FUNC = 'D';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        T000_WRITE_HITORY();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        BPCTFBAS.INFO.FUNC = 'B';
        BPRFBAS.KEY.FEE_CODE = BPCOUBAS.KEY.FEE_CODE;
        if (BPCOUBAS.OPT == 'S') {
            BPCTFBAS.INFO.OPT = 'S';
            S000_CALL_BPZTFBAS();
            if (pgmRtn) return;
        } else if (BPCOUBAS.OPT == 'N') {
            BPCTFBAS.INFO.OPT = 'N';
            S000_CALL_BPZTFBAS();
            if (pgmRtn) return;
            if (BPCTFBAS.RETURN_INFO == 'N') {
                BPCOUBAS.FOUND_FLG = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFBAS.KEY);
