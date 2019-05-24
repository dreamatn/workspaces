package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFQBAC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    char K_STAT_ALL = '3';
    char K_BV_ALL = '2';
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZFQBAC_WS_BACT_KEY WS_BACT_KEY = new BPZFQBAC_WS_BACT_KEY();
    char WS_TBL_BACT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPBACT BPRPBACT = new BPRPBACT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    BPCFQBAC BPCFQBAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFQBAC BPCFQBAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFQBAC = BPCFQBAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFQBAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCFQBAC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_BACT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFQBAC.CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFQBAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFQBAC.STAT != '0' 
            && BPCFQBAC.STAT != '1' 
            && BPCFQBAC.STAT != '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFQBAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFQBAC.BV_KIND != '0' 
            && BPCFQBAC.BV_KIND != '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFQBAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_BACT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBACT);
        IBS.init(SCCGWA, BPCPRMR);
        WS_BACT_KEY.WS_BACT_CODE = BPCFQBAC.CODE;
        WS_BACT_KEY.WS_BACT_STAT = BPCFQBAC.STAT;
        WS_BACT_KEY.WS_BACT_BV_KIND = BPCFQBAC.BV_KIND;
        CEP.TRC(SCCGWA, BPCFQBAC.CODE);
        CEP.TRC(SCCGWA, BPCFQBAC.STAT);
        CEP.TRC(SCCGWA, BPCFQBAC.BV_KIND);
        R000_GET_PARAMETER();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRPBACT);
            WS_BACT_KEY.WS_BACT_CODE = BPCFQBAC.CODE;
            WS_BACT_KEY.WS_BACT_STAT = K_STAT_ALL;
            WS_BACT_KEY.WS_BACT_BV_KIND = BPCFQBAC.BV_KIND;
            R000_GET_PARAMETER();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.init(SCCGWA, BPCPRMR);
                IBS.init(SCCGWA, BPRPBACT);
                WS_BACT_KEY.WS_BACT_CODE = BPCFQBAC.CODE;
                WS_BACT_KEY.WS_BACT_STAT = BPCFQBAC.STAT;
                WS_BACT_KEY.WS_BACT_BV_KIND = K_BV_ALL;
                R000_GET_PARAMETER();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                    IBS.init(SCCGWA, BPCPRMR);
                    IBS.init(SCCGWA, BPRPBACT);
                    WS_BACT_KEY.WS_BACT_CODE = BPCFQBAC.CODE;
                    WS_BACT_KEY.WS_BACT_STAT = K_STAT_ALL;
                    WS_BACT_KEY.WS_BACT_BV_KIND = K_BV_ALL;
                    R000_GET_PARAMETER();
                    if (pgmRtn) return;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BACT_REC_NOTFND, BPCFQBAC.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        BPCFQBAC.ACC_CD = BPRPBACT.DATA_TXT.ACC_CD;
        BPCFQBAC.SUP_FLG = BPRPBACT.DATA_TXT.SUP_FLG;
    }
    public void R000_GET_PARAMETER() throws IOException,SQLException,Exception {
        BPRPBACT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BACT_KEY);
        CEP.TRC(SCCGWA, BPRPBACT.KEY.CD);
        BPRPBACT.KEY.TYP = "BVACT";
        BPCPRMR.DAT_PTR = BPRPBACT;
        CEP.TRC(SCCGWA, BPCPRMR.EFF_DT);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFQBAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFQBAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFQBAC = ");
            CEP.TRC(SCCGWA, BPCFQBAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
