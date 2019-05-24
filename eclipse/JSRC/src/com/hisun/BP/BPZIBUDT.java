package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIBUDT {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CALENDER";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK";
    BPZIBUDT_WS_RC WS_RC = new BPZIBUDT_WS_RC();
    BPZIBUDT_WS_DATE1 WS_DATE1 = new BPZIBUDT_WS_DATE1();
    BPZIBUDT_WS_DATE2 WS_DATE2 = new BPZIBUDT_WS_DATE2();
    int WS_DAYS = 0;
    char WS_REC_DT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCSCALE BPCSCALE = new BPCSCALE();
    SCCGWA SCCGWA;
    BPCIBUDT BPCIBUDT;
    public void MP(SCCGWA SCCGWA, BPCIBUDT BPCIBUDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIBUDT = BPCIBUDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZIBUDT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_GET_BANK_INFO();
        if (BPCIBUDT.DATE1 != 0 
                && BPCIBUDT.DAYS != 0) {
            B030_COMPUTE_BUI_DT2();
        } else if (BPCIBUDT.DATE1 != 0 
                && BPCIBUDT.DATE2 != 0) {
            B040_COMPUTE_BUI_DAYS();
        } else if (BPCIBUDT.DATE2 != 0 
                && BPCIBUDT.DAYS != 0) {
            B050_COMPUTE_BUI_DT1();
        } else {
            B060_CHECK_BUI_DT1();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, BPCIBUDT.DATE1);
        CEP.TRC(SCCGWA, BPCIBUDT.DATE2);
        CEP.TRC(SCCGWA, BPCIBUDT.DAYS);
    }
    public void B020_GET_BANK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQBNK();
    }
    public void B030_COMPUTE_BUI_DT2() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCIBUDT.DATE1+"", WS_DATE1);
        WS_DATE1.WS_DAY1 += 1;
        WS_REC_DT_FLG = 'N';
        while (WS_REC_DT_FLG != 'Y') {
            B100_GET_CALE_INFO();
            B035_COMPUTE_END_DT();
            WS_DATE1.WS_YEAR1 += 1;
            WS_DATE1.WS_MON1 = 1;
            WS_DATE1.WS_DAY1 = 1;
        }
    }
    public void B035_COMPUTE_END_DT() throws IOException,SQLException,Exception {
        while (WS_REC_DT_FLG != 'Y' 
            && WS_DATE1.WS_MON1 <= 12) {
            while (WS_REC_DT_FLG != 'Y' 
                && WS_DATE1.WS_DAY1 <= 31) {
                if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_DATE1.WS_MON1-1].DAY_TYP[WS_DATE1.WS_DAY1-1] == 'W') {
                    WS_DAYS += 1;
                    if (WS_DAYS == BPCIBUDT.DAYS) {
                        WS_REC_DT_FLG = 'Y';
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE1);
                        BPCIBUDT.DATE2 = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
                WS_DATE1.WS_DAY1 += 1;
            }
            WS_DATE1.WS_MON1 += 1;
            WS_DATE1.WS_DAY1 = 1;
        }
    }
    public void B040_COMPUTE_BUI_DAYS() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCIBUDT.DATE1+"", WS_DATE1);
        WS_DATE1.WS_DAY1 += 1;
        WS_REC_DT_FLG = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE1);
        while (JIBS_tmp_str[0].compareTo(BPCIBUDT.DATE2) <= 0) {
            B100_GET_CALE_INFO();
            B045_COMPUTE_CALE_DAYS();
            WS_DATE1.WS_YEAR1 += 1;
            WS_DATE1.WS_MON1 = 1;
            WS_DATE1.WS_DAY1 = 1;
        }
        BPCIBUDT.DAYS = WS_DAYS;
    }
    public void B045_COMPUTE_CALE_DAYS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE1);
        while (JIBS_tmp_str[0].compareTo(BPCIBUDT.DATE2) <= 0 
            && WS_DATE1.WS_MON1 <= 12) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE1);
            while (JIBS_tmp_str[0].compareTo(BPCIBUDT.DATE2) <= 0 
                && WS_DATE1.WS_DAY1 <= 31) {
                if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_DATE1.WS_MON1-1].DAY_TYP[WS_DATE1.WS_DAY1-1] == 'W') {
                    WS_DAYS += 1;
                }
                WS_DATE1.WS_DAY1 += 1;
            }
            WS_DATE1.WS_MON1 += 1;
            WS_DATE1.WS_DAY1 = 1;
        }
    }
    public void B050_COMPUTE_BUI_DT1() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCIBUDT.DATE2+"", WS_DATE1);
        WS_DAYS = BPCIBUDT.DAYS;
        WS_REC_DT_FLG = 'N';
        while (WS_REC_DT_FLG != 'Y') {
            B100_GET_CALE_INFO();
            B055_COMPUTE_STRAT_DT();
            WS_DATE1.WS_YEAR1 -= 1;
            WS_DATE1.WS_MON1 = 12;
            WS_DATE1.WS_DAY1 = 31;
        }
    }
    public void B055_COMPUTE_STRAT_DT() throws IOException,SQLException,Exception {
        while (WS_REC_DT_FLG != 'Y' 
            && WS_DATE1.WS_MON1 >= 1) {
            while (WS_REC_DT_FLG != 'Y' 
                && WS_DATE1.WS_DAY1 >= 1) {
                if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_DATE1.WS_MON1-1].DAY_TYP[WS_DATE1.WS_DAY1-1] == 'W') {
                    WS_DAYS -= 1;
                    if (WS_DAYS < 0) {
                        WS_REC_DT_FLG = 'Y';
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE1);
                        BPCIBUDT.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
                WS_DATE1.WS_DAY1 -= 1;
            }
            WS_DATE1.WS_MON1 -= 1;
            WS_DATE1.WS_DAY1 = 31;
        }
    }
    public void B060_CHECK_BUI_DT1() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCIBUDT.DATE1+"", WS_DATE1);
        B100_GET_CALE_INFO();
        if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_DATE1.WS_MON1-1].DAY_TYP[WS_DATE1.WS_DAY1-1] == 'W') {
            BPCIBUDT.DT_W_FLG = 'Y';
        } else {
            BPCIBUDT.DT_W_FLG = 'N';
        }
    }
    public void B100_GET_CALE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE);
        BPCSCALE.CAL_CODE = BPCPQBNK.DATA_INFO.CALD_BUI;
        BPCSCALE.YEAR = WS_DATE1.WS_YEAR1;
        BPCSCALE.FUNC = 'Q';
        BPCSCALE.OUTPUT_FLG = 'N';
        S000_CALL_BPZSCALE();
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSCALE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CAL_MAINTAIN, BPCSCALE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
